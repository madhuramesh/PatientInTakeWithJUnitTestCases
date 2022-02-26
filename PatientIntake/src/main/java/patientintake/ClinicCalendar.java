package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import java.util.Locale;

public class ClinicCalendar {

    private List<PatientAppointment> appointments;

    public ClinicCalendar(){
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String firstName, String lastName,
                               String doctorkey, String dateTime){

        Doctor doc = Doctor.valueOf(doctorkey.toLowerCase());
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
                    DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));

        } catch (Throwable t) {
            throw new RuntimeException("Unable to create date time from: ["+
                    dateTime.toUpperCase() + "], please enter with format [M/d/yyyy h:mm a]");

        }
        PatientAppointment appointment = new PatientAppointment(firstName, lastName,
                localDateTime, doc);
        appointments.add(appointment);
    }

    public List<PatientAppointment> getAppointments(){
        return this.appointments;
    }

    public boolean hasAppointment(String fname){
        for(PatientAppointment a : appointments) {
            if (a.getPatientFirstName().equals(fname)) {
                System.out.println(a.getPatientFirstName());
                return true;
            }
        }
        return false;
    }

    public List<PatientAppointment> getTodayAppointments(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String now = formatter.format(LocalDateTime.now());
        System.out.println("Today's Appointments");
        List<PatientAppointment> retList = new ArrayList<>();;
        for(PatientAppointment a: appointments){
            String appDate = formatter.format(a.getAppDateTime());
            if (appDate.equals(now)) {
                retList.add(a);
                System.out.println(a.getPatientFirstName()+" "+a.getPatientLastName()+" "+a.getDoctor().getName());
            }
        }
        return retList;
    }

    public List<PatientAppointment> getTomorrowAppointments(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String now = formatter.format(LocalDateTime.now().plusDays(1));

        List<PatientAppointment> retList = new ArrayList<>();;
        for(PatientAppointment a: appointments){
            String appDate = formatter.format(a.getAppDateTime());
            if (appDate.equals(now)) {
                retList.add(a);
                System.out.println(a.getPatientFirstName()+" "+a.getPatientLastName()+" "+a.getDoctor().getName());
            }
        }
        return retList;
    }
}
