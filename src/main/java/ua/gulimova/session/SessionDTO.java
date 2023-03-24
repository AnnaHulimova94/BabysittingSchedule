package ua.gulimova.session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
class SessionDTO {

    @NotNull
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate sessionDate;

    @NotNull
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime sessionStartTime;

    @NotNull
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime sessionEndTime;

    private int employeeId;

    Session convertToSession() {
        return new Session(sessionDate, sessionStartTime, sessionEndTime);
    }
}
