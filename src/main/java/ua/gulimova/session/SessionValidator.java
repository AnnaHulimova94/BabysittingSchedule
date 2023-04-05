package ua.gulimova.session;

import org.springframework.stereotype.Component;
import ua.gulimova.util.ValidationMSG;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Component
public class SessionValidator {

    public List<String> validate(Session session) {
        List<String> fieldValidationMessages = new LinkedList<>();

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
