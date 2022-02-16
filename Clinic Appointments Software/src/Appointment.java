import java.util.Calendar;
import java.util.Locale;

/**
 * Appointment class
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Timeslot getSlot() {
        return slot;
    }

    public void setSlot(Timeslot slot) {
        this.slot = slot;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Appointment(Patient patient, Timeslot slot, Location location) {
        this.patient = patient;
        this.slot = slot;
        this.location = location;
    }
    public Appointment( ) {

    }
    public Appointment(String[] strs) {
        Date patient_dob = new Date(strs[1]);
        Patient patient = new Patient(strs[2], strs[3], patient_dob);
        Date timeslot_dob = new Date(strs[4]);
        String[] time_str = strs[5].split(":");
        Time time = new Time(Integer.valueOf(time_str[0]), Integer.valueOf(time_str[1]));
        Timeslot timeslot = new Timeslot();
        timeslot.setTime(time);
        timeslot.setDate(timeslot_dob);
        Location location = null;
        strs[6] = strs[6].toUpperCase();
        if (strs[6].equals("UNION")) location = Location.UNION;
        if (strs[6].equals("MORRIS")) location = Location.MORRIS;
        if (strs[6].equals("SOMERSET")) location = Location.SOMERSET;
        if (strs[6].equals("MIDDLESEX")) location = Location.MIDDLESEX;
        if (strs[6].equals("MERCER")) location = Location.MERCER;
        this.setLocation(location);
        this.setPatient(patient);
        this.setSlot(timeslot);
    }

    /**
     * if the two equals
     * @param obj
     * @return true if equals, false if the two are not equals
     */
    @Override
    public boolean equals(Object obj) {
        if (patient.compareTo(((Appointment) obj).getPatient()) == 0
                && slot.compareTo(((Appointment) obj).getSlot()) == 0
                && location.compareTo(((Appointment) obj).getLocation()) ==0) return true;
        return false;
    }
    @Override
    public String toString( ) {
        String str = "";
        str += this.patient.getFname() + " " + this.patient.getLname() + ", ";
        Date dob = this.patient.getDob();
        str += "DOB: " + dob.getMonth()+"/"+dob.getDay()+"/"+dob.getYear() + ", ";
        dob = this.slot.getDate();
        str += "Appointment detail: " + dob.getMonth()+"/"+dob.getDay()+"/"+dob.getYear()+", " + this.slot.getTime().toString()+", ";
        str += this.location.getCities() + " " + this.location.getZips() + ", " + this.location.toString();

        return str;
    }
    //test
    public static void main(String[] args) {
        Timeslot slot1 = new Timeslot();
        String test1 = "10/11/2022";
        Date date1 = new Date(test1);
        Time time1 = new Time(5,10);
        slot1.setDate(date1);
        slot1.setTime(time1);
        Patient patient = new Patient("Jane", "Doe", date1);
        Location location = Location.UNION;
        Appointment appointment = new Appointment(patient, slot1, location);
        Appointment appointment2 = new Appointment(patient, slot1, location);
        System.out.println(appointment);
        System.out.println(appointment.equals(appointment2));
    }


}
