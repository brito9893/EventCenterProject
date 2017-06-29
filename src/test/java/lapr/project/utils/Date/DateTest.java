package lapr.project.utils.Date;

import org.junit.*;

import java.util.*;

import static lapr.project.utils.Date.Date.Month.*;
import static lapr.project.utils.Date.Date.WeekDay.*;
import static lapr.project.utils.Date.Date.*;
import static org.junit.Assert.*;

/**
 * Date Unit tests.
 * <p>
 * Created by vitor on 02/06/2017.
 */
public class DateTest {

    @Test
    public void ensureMonthByDefaultIsJanuary() {
        String expected = "January";
        String existant = String.valueOf(JANUARY);

        assertEquals(expected, existant);
    }

    @Test
    public void ensureDateByDefaultIs111() {
        Date expected = new Date(1, 1, 1);
        Date existant = new Date();

        assertEquals(expected, existant);
    }

    @Test
    public void ensureSameContentObjectsAreEqual() {
        Date expected = new Date(2017, 6, 2);
        Date middle = new Date(2017, 6, 2);
        Date start = new Date(middle);
        assertEquals(expected, start);
    }

    @Test
    public void ensureLeapYearVerification() {
        boolean expected = false;
        Date d = new Date(2017, 6, 2);
        boolean actual = isLeapYear(d.getYear());

        assertEquals(expected, actual);
    }

    @Test
    public void ensureGetYearReturnsCorrectYear() {
        int expected = 2017;
        Date d = new Date(2017, 6, 2);
        int actual = d.getYear();

        assertEquals(expected, actual);
    }

    @Test
    public void ensureGetMonthReturnsCorrectMonth() {
        int expected = 6;
        Date d = new Date(2017, 6, 2);
        int actual = d.getMonth();

        assertEquals(expected, actual);
    }

    @Test
    public void ensureGetDayReturnsCorrectDay() {
        int expected = 2;
        Date d = new Date(2017, 6, 2);
        int actual = d.getDay();

        assertEquals(expected, actual);
    }

    @Test
    public void ensureSetDateWorks() {
        boolean thrown = false;

        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;    // january is represented by 0.
        int day = today.get(Calendar.DAY_OF_MONTH);

        Date d = new Date();
        try {
            d.setDate(year, month, day);
        } catch (Exception e) {
            thrown = true;
        }

        assertFalse(thrown);
    }

    @Test
    public void ensureToStringOutputsRightFull() {
        Date d = new Date(2017, 6, 2);

        String expected = "Friday, June 2 2017";
        String actual = d.toString();

        assertEquals(expected, actual);
    }


    @Test
    public void ensureToStringOutputsRightPartial() {
        Date d = new Date(2017, 6, 2);

        String expected = "2017/06/02";
        String actual = d.toYearMonthDayString();

        assertEquals(expected, actual);
    }

    @Test
    public void ensureTwoObjectsEqual() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2017, 6, 2);

        assertEquals(d1, d2);
    }

    @Test
    public void ensureComparesToWorks() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2018, 6, 2);

        int diff = d2.compareTo(d1);
        int expected = 1;

        assertEquals(diff, expected);
    }

    @Test
    public void ensureComparesToFails() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2016, 6, 2);

        int diff = d2.compareTo(d1);
        int expected = 1;

        assertNotEquals(diff, expected);
    }

    @Test
    public void ensureWeekdayIsCorrect() {
        Date d1 = new Date(2017, 6, 10);
        String actual = d1.weekDay();
        String expected = "Saturday";

        assertEquals(actual, expected);
    }

    @Test
    public void ensureDateIsBigger() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2018, 6, 2);

        assertFalse(d1.isBigger(d2));
        assertTrue(d2.isBigger(d1));
    }


    @Test
    public void ensureCurrentDateIsRight() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;    // january is represented by 0.
        int day = today.get(Calendar.DAY_OF_MONTH);
        Date actual = currentDate();
        Date expected = new Date(year, month, day);

        assertEquals(actual, expected);
    }

    @Test
    public void ensureEquals3rdVerificationSucceds() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2017, 6, 2);
        assertEquals(d1, d2);
    }

    @Test
    public void ensureEquals3rdVerificationFails() {
        Date d1 = new Date(2018, 7, 3);
        Date d2 = new Date(2017, 6, 2);
        assertNotEquals(d1, d2);
    }

    @Test
    public void ensureEqualsToNoNullDate() {
        Date n = null;
        Date d2 = new Date(2005, 9, 12);
        assertNotEquals(d2, n);
    }

    @Test
    public void ensureEqualsToObjectClass() {
        Object n = new Object();
        Date d2 = new Date(2005, 9, 12);
        assertNotEquals(d2, n);
    }


    @Test
    public void ensureDifference1Works() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2017, 6, 8);

        int expected = 6;
        int actual = d1.difference(d2);

        assertEquals(expected, actual);
    }

    @Test
    public void ensureDifference2Works() {
        Date d1 = new Date(2017, 6, 2);
        int day = 8;
        int month = 6;
        int year = 2017;

        int expected = 6;
        int diference = d1.difference(year, month, day);

        assertEquals(expected, diference);
    }

    @Test
    public void ensureHashCodeEquals() {
        Date d1 = new Date(2017, 6, 2);
        Date d2 = new Date(2017, 6, 2);
        Assert.assertTrue(d1.equals(d2) && d1.equals(d2));
        Assert.assertTrue(d1.hashCode() == d2.hashCode());
    }

    @Test
    public void ensureEnumHasCorrectWeekdays() {
        String[] actual = {
                SUNDAY.toString(),
                MONDAY.toString(),
                TUESDAY.toString(),
                WEDNESDAY.toString(),
                THURSDAY.toString(),
                FRIDAY.toString(),
                SATURDAY.toString()
        };

        String[] expected = {
                "Sunday",
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        boolean thrown = false;

        for (int i = 0; i < actual.length; i++) {
            assertEquals(actual[i], expected[i]);
        }
    }

    @Test
    public void ensureEnumHasCorrectMonths() {
        String[] actual = {
                JANUARY.toString(),
                FEBRUARY.toString(),
                MARCH.toString(),
                APRIL.toString(),
                MAY.toString(),
                JUNE.toString(),
                JULY.toString(),
                AUGUST.toString(),
                SEPTEMBER.toString(),
                OCTOBER.toString(),
                NOVEMBER.toString(),
                DECEMBER.toString()
        };

        String[] expected = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };

        boolean thrown = false;

        for (int i = 0; i < actual.length; i++) {
            assertEquals(actual[i], expected[i]);
        }

    }

    @Test
    public void ensureLeapYearIsWorking() {
        assertTrue(Date.isLeapYear(2016));
        assertFalse(Date.isLeapYear(2017));
    }

    @Test(expected = InvalidMonthException.class)
    public void ensureSetDateBreaksWithNotExistingMonths() {
        Date d1 = new Date();
        d1.setDate(2017, 13, 1);
    }

    @Test(expected = InvalidDayException.class)
    public void ensureSetDateBreaksWithNotExistingDays() {
        Date d1 = new Date();
        d1.setDate(2017, 3, 32);
    }

    public void ensureSetDateBreaksWithZeroDay() {
        Date d1 = new Date();
        d1.setDate(2017, 3, 0);
    }

    @Test(expected = InvalidDayException.class)
    public void ensureSetDateBreaksWithNotExistingFebrearyDays() {
        Date d1 = new Date();
        d1.setDate(2017, 2, 29);
    }


    @Test
    public void ensureNumberOfFebruaryDaysWorks() {
        Date d1 = new Date(2017, 6, 2);
        int year = d1.getYear();
        int actual = FEBRUARY.numberOfDays(year);
        int expected = 28;

        assertEquals(actual, expected);


    }

    @Test
    public void ensureNumberOfMonthDaysWorks() {
        int actual = JUNE.numberOfDays(2017);
        int expected = 30;

        assertEquals(actual, expected);


    }
}

