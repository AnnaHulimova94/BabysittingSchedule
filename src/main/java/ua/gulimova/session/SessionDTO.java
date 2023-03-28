package ua.gulimova.session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessionDTO {

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    @NotNull
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime sessionStartTime;

    @NotNull
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime sessionEndTime;

    private long hirerId;

    public SessionDTO(Session session, long hirerId) {
        this.sessionDate = session.getSessionDate();
        this.sessionStartTime = session.getSessionStartTime();
        this.sessionEndTime = session.getSessionEndTime();
        this.hirerId = hirerId;
    }

    Session convertToSession() {
        return new Session(sessionDate, sessionStartTime, sessionEndTime);
    }
}
