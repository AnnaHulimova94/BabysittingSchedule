package session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.gulimova.hirer.Hirer;
import ua.gulimova.session.Session;
import ua.gulimova.session.SessionValidator;

import java.time.LocalDate;
import java.time.LocalTime;

class SessionValidatorTest {

    private SessionValidator sessionValidator = new SessionValidator();
    private Hirer hirer = new Hirer();

    @DisplayName("Test date validation")
    @Test
    void test_validate_invalidDateFormat() {
        //Session date is before current date
        Session session1 = new Session(LocalDate.now().minusDays(1),
                LocalTime.now().plusHours(1),
                LocalTime.now().plusHours(2));

        //Session date is correct
        Session session2 = new Session(LocalDate.now(),
                LocalTime.now().plusHours(2),
                LocalTime.now().plusHours(3));

        Assertions.assertEquals("Session date is not correct", sessionValidator.validate(session1, hirer).get(0));
        Assertions.assertTrue(sessionValidator.validate(session2, hirer).isEmpty());
    }

    @DisplayName("Test time validation")
    @Test
    void test_validate_invalidTimeFormat() {
        //Session end time is before start time
        Session session1 = new Session(LocalDate.now(),
                LocalTime.now().plusHours(6),
                LocalTime.now().plusHours(5));

        //Session date is current date, but time is before current time
        Session session2 = new Session(LocalDate.now(),
                LocalTime.now().minusHours(1),
                LocalTime.now().plusHours(1));

        //Session time is correct
        Session session3 = new Session(LocalDate.now(),
                LocalTime.now().plusHours(1),
                LocalTime.now().plusHours(2));

        Assertions.assertEquals("Session time is not correct", sessionValidator.validate(session1, hirer).get(0));
        Assertions.assertEquals("Session time is not correct", sessionValidator.validate(session2, hirer).get(0));
        Assertions.assertTrue(sessionValidator.validate(session3, hirer).isEmpty());
    }

    @DisplayName("Test validation, when employee is not found")
    @Test
    void test_validate_employeeNotFound() {
        //Session time is correct
        Session session = new Session(LocalDate.now(),
                LocalTime.now().plusHours(1),
                LocalTime.now().plusHours(2));

        Assertions.assertEquals("Employee with such id is not found", sessionValidator.validate(session, null).get(0));
        Assertions.assertTrue(sessionValidator.validate(session, hirer).isEmpty());
    }
}
