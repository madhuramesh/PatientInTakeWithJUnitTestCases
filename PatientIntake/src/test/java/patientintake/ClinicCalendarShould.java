package patientintake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;
import patientintake.Doctor;
import patientintake.PatientAppointment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicCalendarShould {

    private ClinicCalendar calendar;

    @BeforeEach
    void init(){
        calendar = new ClinicCalendar();
    }

    @Test
    @DisplayName("allow entry of an appointment")
    void allowEntryOfAnAppointment() {
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2022 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredApp = appointments.get(0);
        /*assertEquals("Jim", enteredApp.getPatientFirstName());
        assertEquals("Weaver", enteredApp.getPatientLastName());
        assertEquals(Doctor.avery, enteredApp.getDoctor());*/

        assertAll(
                () -> assertEquals("Jim", enteredApp.getPatientFirstName()),
                () -> assertEquals("Weaver", enteredApp.getPatientLastName()),
                () -> assertEquals(Doctor.avery, enteredApp.getDoctor())

        );
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments(){
        calendar.addAppointment("Jim", "Weaver", "avery",
                "09/01/2022 2:00 pm");
        assertTrue(calendar.hasAppointment("Jim"));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments(){
        assertFalse(calendar.hasAppointment("Jim"));
    }

    @Nested
    @DisplayName("return appointments correctly")
    class AppointmentsForDay{

        @Test
        @DisplayName("for today")
        void returnCurrentDaysAppointments(){
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "02/24/2022 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "02/24/2022 3:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "02/20/2022 2:00 pm");
            assertEquals(2, calendar.getTodayAppointments().size());
        }

        @Test
        @DisplayName("for tomorrow")
        void returnTomorrowsAppointments(){
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "02/25/2022 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "02/25/2022 3:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "02/20/2022 2:00 pm");
            assertEquals(2, calendar.getTomorrowAppointments().size());
        }
    }
//TODO
    //@Nested
    //@DisplayName("return upcoming appointments")
    //class UpcomingAppointments(){

    //}

}
