import java.util.Scanner;

/**
 * Kiosk class
 * to process the transactions entered
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class Kiosk {
    public void run() {
        Schedule schedule = new Schedule();
        Scanner sc = new Scanner(System.in);
        System.out.println("Kiosk running. Ready to process transactions.\n");
        while (true) {
            String str;
            str = sc.nextLine();
            String[] strs = str.split(" ");
            if (strs[0].equals("Q")) break;
            if (strs[0].equals("B")) {
                Appointment appointment = new Appointment(strs);
                String isFlag = schedule.isValid(appointment);
                if (isFlag.equals("true")) { //add if valid
                    schedule.add(appointment);
                    System.out.println("Appointment booked and added to the schedule.");
                } else {
                    System.out.println(isFlag);
                }
            } else if (strs[0].equals("P")) {
                schedule.print();
            } else if (strs[0].equals("C")) {
                Appointment appointment = new Appointment(strs);
                if (schedule.remove(appointment)) System.out.println("Appointment cancelled.");
                else System.out.println("Not cancelled, appointment does not exist.");
            } else if (strs[0].equals("PZ")) {
                schedule.printByZip();
            }else if (strs[0].equals("CP")) {
                schedule.removeByPatient(strs);
                System.out.println("All appointments for "+ strs[2]+" "+ strs[3] + ", DOB: "+strs[1]+" have been cancelled.");
            }else if (strs[0].equals("PP")) {
                schedule.printByPatient();
            } else {
                System.out.println("Invalid command!");
            }
        }
        System.out.println("Kiosk session ended.");
    }
}
