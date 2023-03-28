package ua.gulimova.session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ua.gulimova.employee.Employee;
import ua.gulimova.hirer.Hirer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Session")
public class Session {

    @Id
    @GeneratedValue
    @Column(name = "session_id")
    private long id;

    @NonNull
    @Column(name = "session_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate sessionDate;

    @NonNull
    @Column(name = "session_start_time")
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime sessionStartTime;

    @NonNull
    @Column(name = "session_end_time")
    @JsonFormat(pattern = "hh:mm:ss")
    private LocalTime sessionEndTime;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name="hirer_id")
    private Hirer hirer;

    public Session(@NonNull LocalDate sessionDate, @NonNull LocalTime sessionStartTime, @NonNull LocalTime sessionEndTime) {
        this.sessionDate = sessionDate;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
    }
}
