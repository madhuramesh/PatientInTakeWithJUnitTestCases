package patientintake;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class ClinicMain {

    private static ClinicCalendar calendar;

    public static void main(String[] args) throws Throwable {
        calendar = new ClinicCalendar();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient Intake Computer System!\n\n");
        String lastOption = "";
        while (!lastOption.equalsIgnoreCase("x")){
            lastOption = displayMenu(scanner);
        }

    }

    private static String displayMenu(Scanner scan) throws Throwable{
        System.out.println("Please select an option:");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. Search Appointment by First name");
        System.out.println("4. Enter Patient Height Weight");
        System.out.println("5. View Today's Appointments");
        System.out.println("6. View Tomorrow's Appointments");
        System.out.println("X. Exit System.");
        System.out.println("Option: ");

        String option = scan.next();
        switch (option) {

            case "1": performPatientEntry(scan);
                        return option;
            case "2": performAllAppointments();
                        return option;
            case "3": searchByAppdate(scan);
                        return option;
            case "4": performHeightWeight(scan);
                        return option;
            case "5": performTodaysAppointment();
                return option;
            case "6": performTomorrowsAppointment();
                return option;

            default: System.out.println("Invalid option, please re-enter.");
                        return option;

        }
    }

    private static void performTomorrowsAppointment(){
        System.out.println("\n\nAll Tomorrow's Appointments in System:");
        calendar.getTomorrowAppointments();
        System.out.println("\nPress any key to continue...");

    }

    private static void performTodaysAppointment(){
        System.out.println("\n\nAll Today's Appointments in System:");
       calendar.getTodayAppointments();
        System.out.println("\nPress any key to continue...");

    }

    private static void performPatientEntry(Scanner scanner){
        scanner.nextLine();
        System.out.println("\n\nPlease enter Appointment Info:");
        System.out.println(" Patient Last Name: ");
        String Lname = scanner.nextLine();
        System.out.println(" Patient First Name: ");
        String Fname = scanner.nextLine();
        System.out.println(" Appointment Date (M/d/yyyy h:m a): ");
        String when = scanner.nextLine();
        System.out.println(" Doctor Last Name: ");
        String doc = scanner.nextLine();
        try{
            calendar.addAppointment(Fname, Lname, doc, when);
        } catch (Throwable t) {
            System.out.println("Error! "+ t.getMessage());
            return;
        }
        System.out.println("Patient entered successfully.\n\n");
    }

    private static void performAllAppointments() throws Throwable{
        System.out.println("\n\nAll Appointments in System:");
        for(PatientAppointment appoint: calendar.getAppointments()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appoint.getAppDateTime());
            System.out.println("Doctor: " + apptTime + "\t\t" + appoint.getPatientFirstName()
                    + "\t\t" + appoint.getPatientLastName() +
                    "\t\t" + appoint.getDoctor().getName());
        }
        System.out.println("\nPress any key to continue...");
        }

        private static void searchByAppdate(Scanner scanner) throws Throwable{
            scanner.nextLine();
            System.out.println("\n\nPlease enter Appointment Date to find:");

            String dateVar = scanner.nextLine();
            System.out.println("\n\nAppointment found for :"+dateVar);
            System.out.println(calendar.hasAppointment(dateVar));
        }

        private static void performHeightWeight(Scanner scanner) throws Throwable{
            scanner.nextLine();
            System.out.println("\n\nEnter patient height and weight for today's Appointment:");

            System.out.println(" Patient Last Name: ");
            String lname = scanner.nextLine();
            System.out.println(" Patient First Name: ");
            String fname = scanner.nextLine();

            Optional<PatientAppointment> appt = findPatientAppointment(lname, fname);
            if (appt!= null){
                System.out.println(" Height in Inches: ");
                Integer ht = scanner.nextInt();
                System.out.println(" Weight in Inches: ");
                Integer wt = scanner.nextInt();
                Double bmi = BMICalculator.calculateBmi(ht,wt);
                //appt.setBMI(Math.floor(bmi));
                System.out.println("Set patient BMI to "+Math.floor(bmi)+"\n\n");
            }
            else{
                System.out.println("Patient not found,\n\n");
            }
    }

    private static Optional<PatientAppointment> findPatientAppointment(String lname, String fname) {
        return calendar.getTodayAppointments().stream()
                .filter(p -> (p.getPatientLastName().equalsIgnoreCase(lname) && p.getPatientFirstName().equalsIgnoreCase(fname)))
                .findFirst();

    }

        /*private static PatientAppointment findPatientAppointment(String lname, String fname) {
            for (PatientAppointment appoint : calendar.getAppointments()) {
                if ((appoint.getPatientFirstName().equals(fname)) && (appoint.getPatientLastName().equals(lname))) {
                    //System.out.println(a.getPatientFirstName());
                    return appoint;
                }
            }
            return null;
        }*/
    }

