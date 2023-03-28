package session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import ua.gulimova.DataResponse;
import ua.gulimova.employee.Employee;
import ua.gulimova.hirer.Hirer;
import ua.gulimova.hirer.HirerService;
import ua.gulimova.session.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionValidator sessionValidator;

    @Mock
    private HirerService hirerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_addValidSession() {
        Session session = new Session(LocalDate.now(), LocalTime.now(), LocalTime.now());
        long hirerId = 101;

        doReturn(new DataResponse<>(new Hirer(), HttpStatus.OK))
                .when(hirerService)
                .get(hirerId);

        doReturn(new ArrayList<>())
                .when(sessionValidator)
                .validate(any(Session.class), any(Hirer.class));

        doAnswer(AdditionalAnswers.returnsFirstArg())
                .when(sessionRepository)
                .save(any());

        DataResponse<Session> dataResponse = sessionService.add(new SessionDTO(session, hirerId));

        Assertions.assertEquals(dataResponse.getHttpStatus(), HttpStatus.OK);
        Assertions.assertNull(dataResponse.getFieldValidationMessages());
        Assertions.assertNotNull(dataResponse.getData().getHirer());

        verify(hirerService).get(hirerId);
        verify(sessionValidator).validate(any(Session.class), any(Hirer.class));
        verify(sessionRepository).save(any());
    }

    @Test
    void test_addInvalidSession() {
        long hirerId = 101;

        doReturn(null)
                .when(hirerService)
                .get(hirerId);

        List<String> strings = new ArrayList<>();
        strings.add("Session is not valid");

        doReturn(strings)
                .when(sessionValidator)
                .validate(any(Session.class), eq(null));

        Session session = new Session(LocalDate.now(), LocalTime.now(), LocalTime.now());

        DataResponse<Session> dataResponse = sessionService.add(new SessionDTO(session, hirerId));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, dataResponse.getHttpStatus());
        Assertions.assertFalse(dataResponse.getFieldValidationMessages().isEmpty());
        Assertions.assertNull(session.getEmployee());

        verify(hirerService).get(hirerId);
        verify(sessionValidator).validate(any(Session.class), eq(null));
    }
}