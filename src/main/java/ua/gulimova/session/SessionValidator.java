package ua.gulimova.session;

import org.springframework.stereotype.Component;
import ua.gulimova.ValidationMSG;
import ua.gulimova.employee.Employee;
import ua.gulimova.employee.EmployeeService;
import ua.gulimova.hirer.Hirer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Component
public class SessionValidator {

    public List<String> validate(Session session, Hirer hirer) {
        List<String> fieldValidationMessages = new LinkedList<>();

        if (hirer == null) {
            fieldValidationMessages.add(ValidationMSG.MSG_EMPLOYEE_NOT_FOUND_MESSAGE);
        }

        if (!isTimeValid(session.getSessionDate(),
                session.getSessionStartTime(),
                session.getSessionEndTime())) {
            fieldValidationMessages.add(ValidationMSG.MSG_SESSION_TIME_IS_INVALID);
        }

        if (!isDateValid(session.getSessionDate())) {
            fieldValidationMessages.add(ValidationMSG.MSG_SESSION_DATE_IS_INVALID);
        }

        return fieldValidationMessages;
    }

    private boolean isDateValid(LocalDate localDate) {
        LocalDate currentDate = LocalDate.now();
        return localDate.equals(currentDate) || localDate.isAfter(currentDate);
    }

    private boolean isTimeValid(LocalDate localDate, LocalTime startTime, LocalTime endTime) {
        if (startTime.isBefore(endTime)) {
            if (localDate.isEqual(LocalDate.now())) {
                return startTime.isAfter(LocalTime.now());
            }

            return true;
        }

        return false;
    }
}
