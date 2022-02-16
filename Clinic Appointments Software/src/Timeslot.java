/**
 * Display time specific
 * compare two timeslot
 * test case
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Timeslot implements Comparable<Timeslot> {
    private Date date;
    private Time time;

    /**
     * format is "month/day/year, hour:minute"
     * @return result date and time
     */
    @Override
    public String toString() {
        String month = Integer.toString(this.date.getMonth());
        String day = Integer.toString(this.date.getDay());
        String year = Integer.toString(this.date.getYear());
        String hourminute = this.time.toString();
        String result =  month + "/" + day + "/" + year + ", " + hourminute;
        return result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * Compare two timeslot
     * Compare years, then months, then days, then hours, then minutes
     * @param slot
     * @return 1 if The original date is later than the given date, -1 if earlier, 0 if same time
     */
    @Override
    public int compareTo(Timeslot slot) {
        if (this.date.getYear() > slot.date.getYear()){
            return 1;
        }else if (this.date.getYear() < slot.date.getYear()){
            return -1;
        }else {
            if (this.date.getMonth() > slot.date.getMonth()){
                return 1;
            }else if (this.date.getMonth() < slot.date.getMonth()){
                return -1;
            }else {
                if (this.date.getDay() > slot.date.getDay()){
                    return 1;
                }else if (this.date.getDay() < slot.date.getDay()){
                    return -1;
                }else {
                    if (this.time.getHour() > slot.time.getHour()){
                        return 1;
                    }else if(this.time.getHour() < slot.time.getHour()){
                        return -1;
                    }else {
                        if (this.time.getMinute() > slot.time.getMinute()){
                            return 1;
                        }else if (this.time.getMinute() < slot.time.getMinute()){
                            return -1;
                        }else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    /**
     * test case
     */
    public static void main(String[] args) {
        //test1 return 0
        Timeslot slot1 = new Timeslot();
        String test1 = "10/11/2022";
        Date date1 = new Date(test1);
        Time time1 = new Time(5,10);
        slot1.date = date1;
        slot1.time = time1;
        Timeslot slot2 = new Timeslot();
        String test2 = "10/11/2022";
        Date date2 = new Date(test1);
        Time time2 = new Time(5,10);
        slot2.date = date2;
        slot2.time = time2;
        System.out.println(slot1.compareTo(slot2));

        //test2 return 1
        Timeslot slot3 = new Timeslot();
        String test3 = "10/12/2024";
        Date date3 = new Date(test3);
        Time time3 = new Time(5,10);
        slot3.date = date3;
        slot3.time = time3;
        Timeslot slot4 = new Timeslot();
        String test4 = "10/11/2022";
        Date date4 = new Date(test4);
        Time time4 = new Time(5,10);
        slot4.date = date4;
        slot4.time = time4;
        System.out.println(slot3.compareTo(slot4));

        //test3 return -1
        Timeslot slot5 = new Timeslot();
        String test5 = "3/11/2022";
        Date date5 = new Date(test5);
        Time time5 = new Time(6,59);
        slot5.date = date5;
        slot5.time = time5;
        Timeslot slot6 = new Timeslot();
        String test6 = "10/11/2022";
        Date date6 = new Date(test6);
        Time time6 = new Time(5,10);
        slot6.date = date6;
        slot6.time = time6;
        System.out.println(slot5.compareTo(slot6));
    }
}