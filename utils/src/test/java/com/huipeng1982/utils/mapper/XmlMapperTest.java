package com.huipeng1982.utils.mapper;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import javax.xml.bind.annotation.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * 演示基于JAXB2.0的Java对象-XML转换及Dom4j的使用.
 * <p>
 * 演示用xml如下:
 *
 * <pre>
 * <?xml version="1.0" encoding="UTF-8"?>
 * <user id="1">
 * 	<name>HuiPeng</name>
 * 	<interests>
 * 		<interest>movie</interest>
 * 		<interest>sports</interest>
 * 	    <interest>girl</interest>
 * 	</interests>
 * </user>
 * </pre>
 */
public class XmlMapperTest {

    /**
     * 使用Dom4j生成测试用的XML文档字符串.
     */
    private static String generateXmlByDom4j() {
        Document document = DocumentHelper.createDocument();

        Element root = document.addElement("user").addAttribute("id", "1");

        root.addElement("name").setText("HuiPeng");

        // List<String>
        Element interests = root.addElement("interests");
        interests.addElement("interest").addText("movie");
        interests.addElement("interest").addText("sports");
        interests.addElement("interest").addText("girl");
        return document.asXML();
    }

    /**
     * 使用Dom4j验证Jaxb所生成XML的正确性.
     */
    private static void assertXmlByDom4j(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            fail(e.getMessage());
        }
        Element user = doc.getRootElement();
        assertThat(user.attribute("id").getValue()).isEqualTo("1");

        Element interests = (Element) doc.selectSingleNode("//interests");
        assertThat(interests.elements()).hasSize(3);
        assertThat(((Element) interests.elements().get(0)).getText()).isEqualTo("movie");
    }

    @Test
    public void objectToXml() {
        User user = new User();
        user.setId(1L);
        user.setName("HuiPeng");

        user.getInterests().add("movie");
        user.getInterests().add("sports");
        user.getInterests().add("girl");

        String xml = XmlMapper.toXml(user, "UTF-8");
        System.out.println("Jaxb Object to Xml result:\n" + xml);
        assertXmlByDom4j(xml);
    }

    @Test
    public void xmlToObject() {
        String xml = generateXmlByDom4j();
        User user = XmlMapper.fromXml(xml, User.class);

        System.out.println("Jaxb Xml to Object result:\n" + user);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getInterests()).containsOnly("movie", "sports", "girl");
    }

    /**
     * 测试以List对象作为根节点时的XML输出
     */
    @Test
    public void toXmlWithListAsRoot() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("HuiPeng");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Kate");

        List<User> userList = Lists.newArrayList(user1, user2);

        String xml = XmlMapper.toXml(userList, "userList", User.class, "UTF-8");
        System.out.println("Jaxb Object List to Xml result:\n" + xml);
    }

    @XmlRootElement
    // 指定子节点的顺序
    @XmlType(propOrder = {"name", "interests"})
    private static class User {

        private Long id;
        private String name;
        private String password;

        private List<String> interests = Lists.newArrayList();

        // 设置转换为xml节点中的属性
        @XmlAttribute
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 设置不转换为xml
        @XmlTransient
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // 设置对List<String>的映射, xml为<interests><interest>movie</interest></interests>
        @XmlElementWrapper(name = "interests")
        @XmlElement(name = "interest")
        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
