package patientintake;

import java.time.LocalDateTime;

public class PatientAppointment {

    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime appDateTime;
    private Doctor doctor;
    private double bmi;

    public PatientAppointment(String firstName, String lastName, LocalDateTime localDateTime, Doctor doc) {
        this.patientFirstName = firstName;
        this.patientLastName = lastName;
        this.appDateTime = localDateTime;
        this.doctor = doc;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public LocalDateTime getAppDateTime() {
        return appDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setBMI(double bmi){
        this.bmi = bmi;
    }

    public double getBMI(){
        return this.bmi;
    }
}
