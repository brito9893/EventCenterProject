package lapr.project.utils.Date;

import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a date through a day, month and year.
 *
 * @author ISEP-DEI-PPROG
 */
@XmlRootElement
@Embeddable
public class Date implements Comparable<Date> {

    /**
     * Year by omission.
     */
    private static final int YEAR_BY_DEFAULT = 1;
    /**
     * Month by omission.
     */
    private static final Month MONTH_BY_DEFAULT = Month.JANUARY;
    /**
     * O day por omissão.
     */
    private static final int DAY_BY_DEFAULT = 1;
    /**
     * O year da date.
     */
    @XmlElement
    private int year;
    /**
     * O mês da date.
     */
    @XmlElement
    private Month month;
    /**
     * O day da date.
     */
    @XmlElement
    private int day;

    /**
     * It builds a date instance by receiving the year, month and day.
     *
     * @param year  date's year.
     * @param month date' month.
     * @param day   date's day.
     */
    public Date(int year, int month, int day) {
        setDate(year, month, day);
    }

    /**
     * Constructs a Date instance with the default date.
     */
    public Date() {
        year = YEAR_BY_DEFAULT;
        month = MONTH_BY_DEFAULT;
        day = DAY_BY_DEFAULT;
    }

    /**
     * Constructs an instance of Date with the month the characteristics of the date
     * received by parameter.
     *
     * @param otherDate The date with the characteristics to be copied.
     */
    public Date(Date otherDate) {
        year = otherDate.year;
        month = otherDate.month;
        day = otherDate.day;
    }

    /**
     * Returns true if the last year per parameter is leap year.
     * If the year passed by parameter is non-leap, returns false.
     *
     * @param year the year to validade.
     * @return True if the last year on parameter is leap-off, otherwise
     * returns false.
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Returns the system date.
     *
     * @return current system date.
     */
    public static Date currentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;    // january is represented by 0.
        int day = today.get(Calendar.DAY_OF_MONTH);
        return new Date(year, month, day);
    }

    /**
     * Returns the date's year.
     *
     * @return date's year
     */
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns date's month.
     *
     * @return date's month.
     */
    public int getMonth() {
        return month.ordinal() + 1;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    /**
     * Returns the date's day.
     *
     * @return date's day.
     */
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Modify the date, year and month.
     *
     * @param year  new year
     * @param month new month
     * @param day   new day
     */
    public final void setDate(int year, int month, int day) {
        if (month < 1 || month > 12) {
            throw new InvalidMonthException("Month " + month + " is invalid!!");
        }
        if (day < 1 || day > Month.getMonth(month).numberOfDays(year)) {
            throw new InvalidDayException("Day " + year + "/" + month + "/" + day
                    + " is invalid!!");
        }

        this.month = Month.getMonth(month);
        this.year = year;
        this.day = day;
    }

    /**
     * Returns the textual description of the date in the format: weekday, day, month, year.
     *
     * @return date's characteristics.
     */
    @Override
    public String toString() {
        return String.format("%s, %s %d %d", weekDay(), month, day, year);
    }

    /**
     * Returns date in format:%04d/%02d/%02d.
     *
     * @return date's characteristics.
     */
    public String toYearMonthDayString() {
        return String.format("%04d/%02d/%02d", year, month.ordinal() + 1, day);
    }

    /**
     * Compares the date with the received object.
     *      *
     *
     * @param otherObject the object to compare with the date.
     * @return true if the received object represents a date equivalent to the
     *      * gives you. Otherwise, returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Date otherDate = (Date) otherObject;
        return year == otherDate.year && month.equals(otherDate.month)
                && day == otherDate.day;
    }

    /**
     * Compares the date with the other date received by parameter.
     *
     * @param otherDate the date to be compared.
     * @return the value 0 if the otherDate received is equal to the date; The value -1 is
     *      OtherDate is later than date; The value 1 if the otherDate is
     *      Prior to date.
     *     
     */
    @Override
    public int compareTo(Date otherDate) {
        if (isBigger(otherDate)) {
            return (otherDate.isBigger(this)) ? -1 : 1;
        } else {
            return (otherDate.isBigger(this)) ? -1 : 0;
        }
    }

    /**
     * Returns the date day of the week.
     *      *
     *
     * @return day of the week of the date.
     *      
     */
    public String weekDay() {
        int totalDays = dayCounter();
        totalDays = totalDays % 7;

        return WeekDay.weekDayDesignation(totalDays);
    }


    /**
     * Returns true if the date is greater than the date received per parameter. If
     *      * A date is less than or equal to the date received by parameter, returns false.
     *
     * @param otherDate the other date with which the date is compared.
     * @return true if the date is greater than the date received per parameter,
     *      * Otherwise returns false.
     */
    public boolean isBigger(Date otherDate) {
        int totalDays = dayCounter();
        int totalDays1 = otherDate.dayCounter();

        return totalDays > totalDays1;
    }

    /**
     * Returns the difference in number of days between the date and the date received by
     *      * parameter.
     *      *
     *
     * @param otherDate the other date with which the date is compared to compute
     *                       * The difference of the number of days.
     *                       * @return difference in number of days between the date and the date received by
     *                       * Parameter.
     *                       
     */
    int difference(Date otherDate) {
        int totalDays = dayCounter();
        int totalDays1 = otherDate.dayCounter();

        return Math.abs(totalDays - totalDays1);
    }

    /**
     * Returns the difference in number of days between the date and the date received by
     *      * Parameter with year, month and day.
     *      *
     *
     * @param year the year of the date with which the date is compared to calculate the
     *                  * Difference of number of days.
     *                  * @param month the month of the date with which the date is compared to calculate the
     *             difference of number of days.
     *                  * @param day o day of date with which date is compared to calculate
     *                  * Difference of number of days
     * @return difference in number of days between the date and the date received by
     *      * Parameter with year, month and day.
     *      
     */
    int difference(int year, int month, int day) {
        int totalDays = dayCounter();
        Date otherDate = new Date(year, month, day);
        int totalDays1 = otherDate.dayCounter();

        return Math.abs(totalDays - totalDays1);
    }

    /**
     * Returns the number of days from the day 1/1/1 to the date.
     *      *
     *
     * @return number of days from the day 1/1/1 to the date.
     *      
     */
    private int dayCounter() {
        int totalDays = 0;

        for (int i = 1; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month.ordinal() + 1; i++) {
            totalDays += Month.getMonth(i).numberOfDays(year);
        }
        totalDays += day;

        return totalDays;
    }

    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.day);
        hash = 53 * hash + Objects.hashCode(this.month);
        hash = 53 * hash + Objects.hashCode(this.year);
        return hash;
    }

    /**
     * Represents weekdays.
     */
    enum WeekDay {

        /**
         * Weekdays
         */
        SUNDAY {
            @Override
            public String toString() {
                return "Sunday";
            }
        },
        MONDAY {
            @Override
            public String toString() {
                return "Monday";
            }
        },
        TUESDAY {
            @Override
            public String toString() {
                return "Tuesday";
            }
        },
        WEDNESDAY {
            @Override
            public String toString() {
                return "Wednesday";
            }
        },
        THURSDAY {
            @Override
            public String toString() {
                return "Thursday";
            }
        },
        FRIDAY {
            @Override
            public String toString() {
                return "Friday";
            }
        },
        SATURDAY {
            @Override
            public String toString() {
                return "Saturday";
            }
        };

        /**
         * Returns the name of the day of the week whose order is received by
         * Parameter.
         *
         * @param weekdayOrder the order of the day of the week between zero and six,
         *                     including. The lowest order corresponds to
         *                     Sunday.
         * @return the designation of the day of the week.
         */
        public static String weekDayDesignation(int weekdayOrder) {
            return WeekDay.values()[weekdayOrder].toString();
        }
    }

    /**
     * Represents the monthes of the year.
     */
    enum Month {

        /**
         * Months of the year.
         */
        JANUARY(31) {
            @Override
            public String toString() {
                return "January";
            }
        },
        FEBRUARY(28) {
            @Override
            public String toString() {
                return "February";
            }
        },
        MARCH(31) {
            @Override
            public String toString() {
                return "March";
            }
        },
        APRIL(30) {
            @Override
            public String toString() {
                return "April";
            }
        },
        MAY(31) {
            @Override
            public String toString() {
                return "May";
            }
        },
        JUNE(30) {
            @Override
            public String toString() {
                return "June";
            }
        },
        JULY(31) {
            @Override
            public String toString() {
                return "July";
            }
        },
        AUGUST(31) {
            @Override
            public String toString() {
                return "August";
            }
        },
        SEPTEMBER(30) {
            @Override
            public String toString() {
                return "September";
            }
        },
        OCTOBER(31) {
            @Override
            public String toString() {
                return "October";
            }
        },
        NOVEMBER(30) {
            @Override
            public String toString() {
                return "November";
            }
        },
        DECEMBER(31) {
            @Override
            public String toString() {
                return "December";
            }
        };

        /**
         * Number of days in a month.
         */
        private int numberOfDays;

        /**
         * Constructs a month with the number of days received per parameter.
         *
         * @param numberOfDays the number of days in the month.
         */
        Month(int numberOfDays) {
            this.numberOfDays = numberOfDays;
        }

        /**
         * Returns the month whose order is received per parameter.
         *
         * @param monthOrder the order of the month.
         * @return the month whose order is received per parameter.
         */
        public static Month getMonth(int monthOrder) {
            return Month.values()[monthOrder - 1];
        }

        /**
         * Returns the number of days in the month of the year received per parameter.
         *
         * @param year the year of the month.
         * @return the number of months of the month.
         */
        public int numberOfDays(int year) {
            if (ordinal() == 1 && Date.isLeapYear(year)) {
                return numberOfDays + 1;
            }
            return numberOfDays;
        }

    }
}
