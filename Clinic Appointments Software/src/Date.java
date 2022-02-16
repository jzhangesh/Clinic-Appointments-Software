import java.util.Calendar;

/**
 * Date class
 * Check whether the date format is correct
 * Check whether it is a Leap year
 * Compare the time with the present and decide whether it is the future, past or present
 * test case
 *
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * take “mm/dd/yyyy” and create a Date object
     * @param date date of string type
     */
    public Date(String date) {
        String[] strings = date.split("/",3);
        this.month = Integer.parseInt(strings[0]);
        this.day = Integer.parseInt(strings[1]);
        this.year = Integer.parseInt(strings[2]);
    }

    /**
     * create an object with today’s date (see Calendar class)
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH) + 1;
        this.day = today.get(Calendar.DATE);
    }

    /**
     * get the year of the date
     * @return year
     */
    public int getYear(){
        return year;
    }

    /**
     * get the month of the date
     * @return month
     */
    public int getMonth(){
        return month;
    }

    /**
     * get the day of the date
     * @return day
     */
    public int getDay(){
        return day;
    }

    /**
     * Check if the date is correct format
     * January, March, May, July, August, October and December, each has 31 days
     * April, June, September and November, each has 30 days
     *  February has 28 days in a non-leap year, and 29 days in a leap year
     * @return true if is correct format, false otherwise
     */
    public boolean isValid() {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            return day <= 31 && day >= 1;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11){
            return day <= 30 && day >= 1;
        }
        if (month == 2){
            if (isLeapYear(year)){
                return day <= 29 && day >= 1;
            }else {
                return day <= 28 && day >= 1;
            }
        }
        return false;
    }

    /**
     * determine whether a year is a leap year
     * @param year a year be determined
     * @return true if is leap year, false otherwise
     */
    public boolean isLeapYear(int year){
        if (year % 4 == 0){
            if(year % 100 == 0){
                return year % 400 == 0;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    /**
     * compare two dates
     * Compare years, months and days
     * @param date The date to be compared
     * @return 1 if parameter is smaller, 0 is equal, -1 is larger
     */
    @Override
    public int compareTo(Date date) {
        if (date.year > this.year){
            return -1;
        }else if (date.year < this.year){
            return 1;
        }else {
            if (date.month > this.month){
                return -1;
            }else if (date.month < this.month){
                return 1;
            }else {
                if (date.day > this.day){
                    return -1;
                }else if (date.day < this.day){
                    return 1;
                }else {
                    return 0;
                }
            }
        }
    }

    /**
     * test cases to thoroughly test the isValid() method
     * test 6 different date
     */
    public static void main(String arg[]){
        String test1 = "10/11/2022"; //True
        Date date1 = new Date(test1);
        boolean valid1 = date1.isValid();
        System.out.println(valid1);
        System.out.println(date1 + "===========");

        String test2 = "2/6/2022"; //True
        Date date2 = new Date(test2);
        boolean valid2 = date2.isValid();
        System.out.println(valid2);

        String test3 = "13/10/2010"; //false
        Date date3 = new Date(test3);
        boolean valid3 = date3.isValid();
        System.out.println(valid3);

        String test4 = "2/29/2003"; //false
        Date date4 = new Date(test4);
        boolean valid4 = date4.isValid();
        System.out.println(valid4);

        String test5 = "2/29/2000"; //true
        Date date5 = new Date(test5);
        boolean valid5 = date5.isValid();
        System.out.println(valid5);

        String test6 = "11/31/2003"; //false
        Date date6 = new Date(test6);
        boolean valid6 = date6.isValid();
        System.out.println(valid6);
    }
}
