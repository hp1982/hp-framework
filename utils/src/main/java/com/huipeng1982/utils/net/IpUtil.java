package com.huipeng1982.utils.net;

import com.google.common.net.InetAddresses;
import com.huipeng1982.utils.number.NumberUtil;
import com.huipeng1982.utils.text.MoreStringUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * InetAddress工具类，基于Guava的InetAddresses.
 * <p>
 * 主要包含int, String/IPV4String, InetAdress/Inet4Address之间的互相转换
 * <p>
 * 先将字符串传换为byte[]再用InetAddress.getByAddress(byte[])，避免了InetAddress.getByName(ip)可能引起的DNS访问.
 * <p>
 * InetAddress与String的转换其实消耗不小，如果是有限的地址，建议进行缓存.
 */
public class IpUtil {

    private static final int IPV4_SIZE = 4;
    private static final byte[] EMPTY_BYTE_ARR = new byte[0];

    private IpUtil() {
    }

    /**
     * 从InetAddress转化到int, 传输和存储时, 用int代表InetAddress是最小的开销.
     * <p>
     * InetAddress可以是IPV4或IPV6，都会转成IPV4.
     *
     * @see com.google.common.net.InetAddresses#coerceToInteger(InetAddress)
     */
    public static int toInt(InetAddress address) {
        return InetAddresses.coerceToInteger(address);
    }

    /**
     * InetAddress转换为String.
     * <p>
     * InetAddress可以是IPV4或IPV6. 其中IPV4直接调用getHostAddress()
     *
     * @see com.google.common.net.InetAddresses#toAddrString(InetAddress)
     */
    public static String toIpString(InetAddress address) {
        return InetAddresses.toAddrString(address);
    }

    /**
     * 从int转换为Inet4Address(仅支持IPV4)
     */
    public static Inet4Address fromInt(int address) {
        return InetAddresses.fromInteger(address);
    }

    /**
     * 从String转换为InetAddress.
     * <p>
     * IpString可以是ipv4 或 ipv6 string, 但不可以是域名.
     * <p>
     * 先字符串传换为byte[]再调getByAddress(byte[])，避免了调用getByName(ip)可能引起的DNS访问.
     */
    public static InetAddress fromIpString(String address) {
        return InetAddresses.forString(address);
    }

    /**
     * 从IPv4String转换为InetAddress.
     * <p>
     * IpString如果确定ipv4, 使用本方法减少字符分析消耗 .
     * <p>
     * 先字符串传换为byte[]再调getByAddress(byte[])，避免了调用getByName(ip)可能引起的DNS访问.
     */
    public static Inet4Address fromIpv4String(String address) {
        byte[] bytes = ip4StringToBytes(address);
        if (bytes.length == 0) {
            return null;
        } else {
            try {
                return (Inet4Address) Inet4Address.getByAddress(bytes);
            } catch (UnknownHostException e) {
                throw new AssertionError(e);
            }
        }
    }

    /**
     * int转换到IPV4 String, from Netty NetUtil
     */
    public static String intToIpv4String(int i) {
        return new StringBuilder(15).append((i >> 24) & 0xff).append('.').append((i >> 16) & 0xff).append('.')
            .append((i >> 8) & 0xff).append('.').append(i & 0xff).toString();
    }

    /**
     * Ipv4 String 转换到int
     */
    public static int ipv4StringToInt(String ipv4Str) {
        byte[] byteAddress = ip4StringToBytes(ipv4Str);
        if (byteAddress.length == 0) {
            return 0;
        } else {
            return NumberUtil.toInt(byteAddress);
        }
    }

    /**
     * Ipv4 String 转换到byte[]
     */
    private static byte[] ip4StringToBytes(String ipv4Str) {
        if (ipv4Str == null) {
            return EMPTY_BYTE_ARR;
        }

        List<String> it = MoreStringUtil.split(ipv4Str, '.', 4);
        if (it.size() != IPV4_SIZE) {
            return EMPTY_BYTE_ARR;
        }

        byte[] byteAddress = new byte[4];
        for (int i = 0; i < IPV4_SIZE; i++) {
            int tempInt = Integer.parseInt(it.get(i));
            if (tempInt > 255) {
                return EMPTY_BYTE_ARR;
            }
            byteAddress[i] = (byte) tempInt;
        }
        return byteAddress;
    }
}

