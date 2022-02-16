/**
 * Identify the patient's information name and date of birth
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

    public Patient(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    public Patient( ) {

    }

    /**
     * Turn the patient's birthdate and name into a string
     * @return Patient information
     */
    @Override
    public String toString() {
        String birth = null;
        if (this.dob.isValid()) {
            String birthy = Integer.toString(this.dob.getYear());
            String birthm = Integer.toString(this.dob.getMonth());
            String birthd = Integer.toString(this.dob.getDay());
            birth = birthm + "/" + birthd + "/" + birthy;
        }
        return birth + " " + this.fname + " " + this.lname;
    }

    /**
     * get first name of patient
     * @return fname first name
     */
    public String getfname(){
        return fname;
    }

    /**
     * get last name of patient
     * @return lname last name
     */
    public String getlname(){
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    /**
     * get the date of birth
     * @return birthday date of birth
     */
    public String birth(){
        String birthy = Integer.toString(this.dob.getYear());
        String birthm = Integer.toString(this.dob.getMonth());
        String birthd = Integer.toString(this.dob.getDay());
        String birthday = birthm + "/" + birthd + "/" + birthy;
        return birthday;
    }

    /**
     * Compare two information and determine whether the same person is
     * @param patient The patient who was compared
     * @return 1 if original patient larger, -1 smaller, 0 equal
     */
    @Override
    public int compareTo(Patient patient) {
        //update .............  要改成比较last name firstname 最后生日
        if (this.lname.compareTo(patient.lname) > 0){
            return 1;
        }else if (this.lname.compareTo(patient.lname) < 0){
            return -1;
        }else {
            if (this.fname.compareTo(patient.fname) > 0){
                return 1;
            }else if (this.fname.compareTo(patient.fname) < 0){
                return -1;
            }else {
                if (this.dob.compareTo(patient.dob) > 0){
                    return 1;
                }else if (this.dob.compareTo(patient.dob) < 0){
                    return -1;
                }else {
                    return 0;
                }
            }
        }
    }
}