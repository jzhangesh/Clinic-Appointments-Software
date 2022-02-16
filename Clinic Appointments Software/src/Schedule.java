import java.util.Calendar;

/**
 * Schedule class
 * increase the capacity of the container by 4 when full
 * print all the appointments in current order
 * sort by zip codes and print
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Schedule {
    private Appointment [] appointments;
    private int numAppts;

    public int find(Appointment appt) {
        for (int i = 0; i < this.appointments.length; i++){
            if (this.appointments[i] == appt){
                return i;
            }
        }
        return -1;
    } //return the index, or NOT_FOUND

    /**
     * increase the capacity of the container by 4
     */
    private void grow() {
        int len = appointments.length + 4; //add 4
        Appointment[] appointment_tem = new Appointment[len];
        for (int i = 0; i < numAppts; i++) {
            appointment_tem[i] = appointments[i];
        }
        appointments = appointment_tem;
    }

    public boolean add(Appointment appt) {
        //---------------------update-----------------
        if (this.numAppts == this.appointments.length) {
            this.grow();
        }
        this.appointments[this.numAppts] = appt;
        this.numAppts = this.numAppts + 1;
        return true;
    }

    public boolean remove(Appointment appt) {
        Appointment[] appointment_tem = new Appointment[this.appointments.length];
        int idx = 0;
        boolean find = false;
        for (int i = 0; i < numAppts; i++){
            if (this.appointments[i].equals(appt)){
                find = true;
                continue;
            }
            appointment_tem[idx++] = this.appointments[i];
        }
        this.appointments = appointment_tem;
        if (find) numAppts -=1;
        return find;
    }

    /**
     * print all the appointments in current order
     */
    public void print() {
        for (int i = 0; i < this.appointments.length; i++){
            if (this.appointments[i] != null){
                System.out.println(this.appointments[i]);
            }
        }
    }

    public void printByZip() {

        for (int i = 0; i < this.appointments.length; i++){
            if (this.appointments[i] != null) {
                Appointment appt = this.appointments[i];
                int j = i - 1;
                while (j >= 0
                        && this.appointments[j].getLocation().getZips().compareTo(appt.getLocation().getZips()) > 0) {
                    this.appointments[j + 1] = this.appointments[j];
                    j = j - 1;
                }
                while (j >= 0
                        && this.appointments[j].getLocation().getZips().compareTo(appt.getLocation().getZips()) == 0) {
                    if (this.appointments[j].getSlot().compareTo(appt.getSlot()) == 1) {
                        this.appointments[j + 1] = this.appointments[j];
                        j = j - 1;
                    }else {
                        break;
                    }
                }
                this.appointments[j + 1] = appt;
            }
        }

        for (int i = 0; i < this.appointments.length; i++){
            if (this.appointments[i] != null){
                System.out.println(this.appointments[i]);
            }
        }
    } //sort by zip codes and print

    public void printByPatient() {

        for (int i = 0; i < this.appointments.length; i++){
            if (this.appointments[i] != null) {
                Appointment appt = this.appointments[i];
                int j = i - 1;
                if (j >= 0) {
                    if (this.appointments[j] != null) {
                        while (j >= 0 && this.appointments[j].getPatient().compareTo(appt.getPatient()) == 1) {
                            this.appointments[j + 1] = this.appointments[j];
                            j = j - 1;
                        }
                    }
                }
                this.appointments[j + 1] = appt;
            }
        }

        for (int i = 0; i < this.appointments.length; i++){
            if (this.appointments[i] != null){
                System.out.println(this.appointments[i]);
            }
        }
    } //sort by patient and print

    //---------------------update-----------------
    public Schedule() {
        this.appointments = new Appointment[4];
        this.numAppts = 0;
    }

    //---------------------update-----------------
    public void removeByPatient(String[] strs) {
        //CP 8/31/1978 Jane Doe
        Date patient_dob = new Date(strs[1]);
        Patient patient = new Patient(strs[2], strs[3], patient_dob);
        Appointment[] appts = new Appointment[this.appointments.length];
        int len = 0;
        for (int i = 0; i < numAppts; i++) {
            if (this.appointments[i] != null) {
                if (appointments[i].getPatient().compareTo(patient) == 0) {
                    appts[len++] = appointments[i];
                }
            }
        }
        for (int i = 0 ; i < len; i++) {
            remove(appts[i]);
        }
    }
    //-------------------update--------------------------
    public String isValid(Appointment appointment) {
        /**
         * Determine if exist
         */
        if (this.find(appointment) != -1) return "Same appointment exists in the schedule.";

        if (!appointment.getPatient().getDob().isValid()) return "Invalid date of birth!";
        /**
         * Date of birth is valid, Continue to determine
         */
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);

        Date date = appointment.getPatient().getDob();
        if (date.getYear() > year) return "Date of birth invalid -> it is a future date.";
        else if (date.getYear() == year && month < date.getMonth())
            return "Date of birth invalid -> it is a future date.";
        else if (date.getYear() == year && month == date.getMonth() && day < date.getDay())
            return "Date of birth invalid -> it is a future date.";
        /**
         * Next, process Timeslot's Date
         */
        Date timeslot_date = appointment.getSlot().getDate();
        if (!timeslot_date.isValid() || timeslot_date.getYear() > year) return "Invalid appointment date!";
        /**
         * check the if the appointment time is in the future
         */
        if (timeslot_date.getYear() < year) {
            return "Appointment date invalid -> must be a future date.";
        } else if (timeslot_date.getYear() == year && month > timeslot_date.getMonth()) {
            return "Appointment date invalid -> must be a future date.";
        }
        else if (timeslot_date.getYear() == year && month == timeslot_date.getMonth() && day > timeslot_date.getDay())
        {
            return "Appointment date invalid -> must be a future date.";
        }

        /**
         * determine the time interval
         */
        Time time = appointment.getSlot().getTime();
        if (time.getHour() < 9 || time.getHour() > 16)
            return "Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.";
        else {
            if (!(time.getMinute() == 0 || time.getMinute() == 15 || time.getMinute() == 30 || time.getMinute() == 45))
                return "Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.";
        }
        if (appointment.getLocation() == null) return "Invalid location!";

        /**
         * determine for the same patient at same day, whether patient has appointment at different location
         */
        for (int i = 0; i < numAppts; i++) {
            if (this.appointments[i] != null) {
                if (appointments[i].getPatient().compareTo(appointment.getPatient()) == 0
                        && appointments[i].getSlot().getDate().compareTo(appointment.getSlot().getDate()) == 0){
                    if (appointments[i].getLocation().getCities().compareTo(appointment.getLocation().getCities()) != 0) {
                        return "Same patient cannot book an appointment with the same date.";
                    }
                }
            }
        }

        /**
         * same patient cannot make appointment on same date
         */
        for (int i = 0; i < numAppts; i++) {
            if (this.appointments[i] != null) {
                if (appointments[i].getSlot().compareTo(appointment.getSlot()) == 0
                        && appointments[i].getPatient().compareTo(appointment.getPatient()) == 0)
                    return "Same appointment exists in the schedule.";
            }
        }

        /**
         * determine whether timeslot already exists
         */
        for (int i = 0; i < numAppts; i++) {
            if (this.appointments[i] != null) {
                if (appointments[i].getSlot().compareTo(appointment.getSlot()) == 0
                        && appointments[i].getLocation().compareTo(appointment.getLocation()) == 0)
                    return "Time slot has been taken at this location.";
            }
        }
        return "true";
    }
}