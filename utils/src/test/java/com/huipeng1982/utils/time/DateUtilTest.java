package com.huipeng1982.utils.time;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class DateUtilTest {

    @Test
    public void isSameDay() {
        Date date1 = new GregorianCalendar(2006, 10, 1).getTime();
        Date date2 = new GregorianCalendar(2006, 10, 1, 12, 23, 44).getTime();
        assertThat(DateUtil.isSameDay(date1, date2)).isTrue();

        Date date3 = new GregorianCalendar(2006, 10, 1).getTime();
        assertThat(DateUtil.isSameTime(date1, date3)).isTrue();

        Date date5 = new GregorianCalendar(2006, 10, 2).getTime();
        assertThat(DateUtil.isSameTime(date1, date5)).isFalse();

        Date date4 = new GregorianCalendar(2006, 10, 1, 12, 23, 43).getTime();
        assertThat(DateUtil.isBetween(date3, date1, date2)).isTrue();
        assertThat(DateUtil.isBetween(date4, date1, date2)).isTrue();

        try {
            DateUtil.isBetween(null, date1, date2);
            fail("should fail before");
        } catch (Exception e) {

        }

        try {
            DateUtil.isBetween(date3, date2, date1);
            fail("should fail before");
        } catch (Exception e) {

        }

        assertThat(DateUtil.isBetween(date5, date1, date2)).isFalse();
    }

    @Test
    public void truncateAndCelling() {
        // Sat Jan 21 12:12:12 CST 2017
        Date date = new GregorianCalendar(2017, 0, 21, 12, 12, 12).getTime();

        Date beginYear = new GregorianCalendar(2017, 0, 1, 0, 0, 0).getTime();
        Date endYear = new Date(new GregorianCalendar(2017, 11, 31, 23, 59, 59).getTime().getTime() + 999);
        Date nextYear = new GregorianCalendar(2018, 0, 1, 0, 0, 0).getTime();

        Date beginMonth = new GregorianCalendar(2017, 0, 1).getTime();
        Date endMonth = new Date(new GregorianCalendar(2017, 0, 31, 23, 59, 59).getTime().getTime() + 999);
        Date nextMonth = new GregorianCalendar(2017, 1, 1).getTime();

        Date beginWeek = new GregorianCalendar(2017, 0, 16).getTime();
        Date endWeek = new Date(new GregorianCalendar(2017, 0, 22, 23, 59, 59).getTime().getTime() + 999);
        Date nextWeek = new GregorianCalendar(2017, 0, 23).getTime();

        Date beginDate = new GregorianCalendar(2017, 0, 21).getTime();
        Date endDate = new Date(new GregorianCalendar(2017, 0, 21, 23, 59, 59).getTime().getTime() + 999);
        Date nextDate = new GregorianCalendar(2017, 0, 22).getTime();

        Date beginHour = new GregorianCalendar(2017, 0, 21, 12, 0, 0).getTime();
        Date endHour = new Date(new GregorianCalendar(2017, 0, 21, 12, 59, 59).getTime().getTime() + 999);
        Date nextHour = new GregorianCalendar(2017, 0, 21, 13, 0, 0).getTime();

        Date beginMinute = new GregorianCalendar(2017, 0, 21, 12, 12, 0).getTime();
        Date endMinute = new Date(new GregorianCalendar(2017, 0, 21, 12, 12, 59).getTime().getTime() + 999);
        Date nextMinute = new GregorianCalendar(2017, 0, 21, 12, 13, 0).getTime();

        assertThat(DateUtil.isSameTime(DateUtil.beginOfYear(date), beginYear)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.endOfYear(date), endYear)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.nextYear(date), nextYear)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.beginOfMonth(date), beginMonth)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.endOfMonth(date), endMonth)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.nextMonth(date), nextMonth)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.beginOfWeek(date), beginWeek)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.endOfWeek(date), endWeek)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.nextWeek(date), nextWeek)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.beginOfDate(date), beginDate)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.endOfDate(date), endDate)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.nextDate(date), nextDate)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.beginOfHour(date), beginHour)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.endOfHour(date), endHour)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.nextHour(date), nextHour)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.beginOfMinute(date), beginMinute)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.endOfMinute(date), endMinute)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.nextMinute(date), nextMinute)).isTrue();
    }

    @Test
    public void changeDay() {
        Date date = new GregorianCalendar(2006, 10, 1, 12, 23, 44).getTime();
        Date expectDate1 = new GregorianCalendar(2006, 10, 3).getTime();
        Date expectDate2 = new GregorianCalendar(2006, 9, 31).getTime();
        Date expectDate3 = new GregorianCalendar(2006, 11, 1).getTime();
        Date expectDate4 = new GregorianCalendar(2006, 7, 1).getTime();
        Date expectDate5 = new GregorianCalendar(2006, 10, 1, 13, 23, 44).getTime();
        Date expectDate6 = new GregorianCalendar(2006, 10, 1, 10, 23, 44).getTime();
        Date expectDate7 = new GregorianCalendar(2006, 10, 1, 12, 24, 44).getTime();
        Date expectDate8 = new GregorianCalendar(2006, 10, 1, 12, 21, 44).getTime();

        Date expectDate9 = new GregorianCalendar(2006, 10, 1, 12, 23, 45).getTime();
        Date expectDate10 = new GregorianCalendar(2006, 10, 1, 12, 23, 42).getTime();

        Date expectDate11 = new GregorianCalendar(2006, 10, 8).getTime();
        Date expectDate12 = new GregorianCalendar(2006, 9, 25).getTime();

        assertThat(DateUtil.isSameDay(DateUtil.addDays(date, 2), expectDate1)).isTrue();
        assertThat(DateUtil.isSameDay(DateUtil.subDays(date, 1), expectDate2)).isTrue();

        assertThat(DateUtil.isSameDay(DateUtil.addWeeks(date, 1), expectDate11)).isTrue();
        assertThat(DateUtil.isSameDay(DateUtil.subWeeks(date, 1), expectDate12)).isTrue();

        assertThat(DateUtil.isSameDay(DateUtil.addMonths(date, 1), expectDate3)).isTrue();
        assertThat(DateUtil.isSameDay(DateUtil.subMonths(date, 3), expectDate4)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.addHours(date, 1), expectDate5)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.subHours(date, 2), expectDate6)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.addMinutes(date, 1), expectDate7)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.subMinutes(date, 2), expectDate8)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.addSeconds(date, 1), expectDate9)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.subSeconds(date, 2), expectDate10)).isTrue();

    }

    @Test
    public void setDay() {
        Date date = new GregorianCalendar(2016, 10, 1, 10, 10, 1).getTime();
        Date expectedDate = new GregorianCalendar(2016, 10, 3).getTime();
        Date expectedDate2 = new GregorianCalendar(2016, 10, 1).getTime();
        Date expectedDate3 = new GregorianCalendar(2017, 10, 1).getTime();
        Date expectedDate4 = new GregorianCalendar(2016, 10, 1, 9, 10, 1).getTime();
        Date expectedDate5 = new GregorianCalendar(2016, 10, 1, 10, 9, 1).getTime();
        Date expectedDate6 = new GregorianCalendar(2016, 10, 1, 10, 10, 10).getTime();

        assertThat(DateUtil.isSameDay(DateUtil.setDays(date, 3), expectedDate)).isTrue();
        assertThat(DateUtil.isSameDay(DateUtil.setMonths(date, 11), expectedDate2)).isTrue();
        assertThat(DateUtil.isSameDay(DateUtil.setYears(date, 2017), expectedDate3)).isTrue();

        assertThat(DateUtil.isSameTime(DateUtil.setHours(date, 9), expectedDate4)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.setMinutes(date, 9), expectedDate5)).isTrue();
        assertThat(DateUtil.isSameTime(DateUtil.setSeconds(date, 10), expectedDate6)).isTrue();

    }

    @Test
    public void getDayOfWeek() {
        // 2017-01-09
        Date date = new GregorianCalendar(2017, 0, 9).getTime();
        assertThat(DateUtil.getDayOfWeek(date)).isEqualTo(1);

        Date date2 = new GregorianCalendar(2017, 0, 15).getTime();
        assertThat(DateUtil.getDayOfWeek(date2)).isEqualTo(7);
    }

    @Test
    public void isLeapYear() {
        // 2008-01-09,整除4年, true
        Date date = new GregorianCalendar(2008, 0, 9).getTime();
        assertThat(DateUtil.isLeapYear(date)).isTrue();

        // 2000-01-09,整除400年，true
        date = new GregorianCalendar(2000, 0, 9).getTime();
        assertThat(DateUtil.isLeapYear(date)).isTrue();

        // 1900-01-09，整除100年，false
        date = new GregorianCalendar(1900, 0, 9).getTime();
        assertThat(DateUtil.isLeapYear(date)).isFalse();
    }

    @Test
    public void getXXofXX() {
        // 2008-02-09, 整除4年, 闰年
        Date date = new GregorianCalendar(2008, 2, 9).getTime();
        assertThat(DateUtil.getMonthLength(date)).isEqualTo(29);

        // 2009-02-09, 整除4年, 非闰年
        Date date2 = new GregorianCalendar(2009, 2, 9).getTime();
        assertThat(DateUtil.getMonthLength(date2)).isEqualTo(28);

        Date date3 = new GregorianCalendar(2008, 8, 9).getTime();
        assertThat(DateUtil.getMonthLength(date3)).isEqualTo(31);

        Date date4 = new GregorianCalendar(2009, 11, 30).getTime();
        assertThat(DateUtil.getDayOfYear(date4)).isEqualTo(364);

        Date date5 = new GregorianCalendar(2017, 0, 12).getTime();
        assertThat(DateUtil.getWeekOfMonth(date5)).isEqualTo(3);
        assertThat(DateUtil.getWeekOfYear(date5)).isEqualTo(3);
    }
}
