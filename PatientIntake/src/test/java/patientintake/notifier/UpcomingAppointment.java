package patientintake.notifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpcomingAppointment {

    private EmailNotifierTestDouble emailDouble;

    @BeforeEach
    void init(){
        emailDouble = new EmailNotifierTestDouble();
    }

    @Test
    void sendNotificationWithCorrectFormat(){
        ClinicCalendar calendar = new ClinicCalendar();
        calendar.addAppointment("jim", "weaver", "avery",
                "02/25/2022 2:00 pm");
        UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailDouble);
        notifier.run();

        assertEquals(1,emailDouble.receivedMessages.size());
        EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
        assertAll(
                () -> assertEquals("weaverjim@gmail.com",expectedMessage.toAddress),
                () -> assertEquals("Appointment reminder",expectedMessage.subject),
                () -> assertEquals("You have an appointment tomorrow at 2:00 PM"+
                        " with Dr. Ralph Avery.",expectedMessage.body)


        );


    }
}
