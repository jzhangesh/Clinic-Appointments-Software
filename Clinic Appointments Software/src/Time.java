/**
 * Time class
 * Compare the size of two time objects
 * determine if the time is valid
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class Time  implements Comparable<Time>{
    private int hour;
    private int minute;

    /**
     * parameterized constructor
     * @param houre
     * @param minute
     */
    public Time(int houre, int minute) {
        this.hour = houre;
        this.minute = minute;
    }

    /**
     * no-arg constructor
     */
    public Time( ) {
        hour = 0;
        minute = 0;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Compare the size of two time objects
     * @param o
     * @return 1 if parameter is smaller; -1 if is larger; 0 if equal
     */
    @Override
    public int compareTo(Time o) {
        if (hour > o.hour) {
            return 1;
        } else if (hour < o.hour) {
            return -1;
        } else {
            if (minute == o.minute) return 0;
            return minute > o.minute ? 1 : - 1;
        }
    }

    /**
     * Determine if the time is valid
     * @return ture if time is valid, otherwise return false
     */
    public boolean isValid() {
        if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) return true;
        return false;
    }

    @Override
    public String toString() {
        if (minute < 10)
            return hour + ":0" + minute;
        return hour + ":" + minute;
    }


}
