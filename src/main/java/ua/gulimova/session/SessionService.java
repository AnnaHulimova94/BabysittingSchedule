package ua.gulimova.session;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.gulimova.util.DataResponse;
import ua.gulimova.employee.Employee;
import ua.gulimova.employee.EmployeeService;
import ua.gulimova.hirer.Hirer;
import ua.gulimova.hirer.HirerService;

import java.util.List;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    private EmployeeService employeeService;

    private HirerService hirerService;

    private SessionValidator sessionValidator;

    public SessionService(SessionRepository sessionRepository,
                          EmployeeService employeeService,
                          SessionValidator sessionValidator,
                          HirerService hirerService) {
        this.sessionRepository = sessionRepository;
        this.employeeService = employeeService;
        this.sessionValidator = sessionValidator;
        this.hirerService = hirerService;
    }

    public DataResponse<Session> add(SessionDTO sessionDTO) {
        Session session = sessionDTO.convertToSession();
        DataResponse<Hirer> hirer = hirerService.get(sessionDTO.getHirerId());

        if (hirer == null) {
            return new DataResponse<>(null, HttpStatus.NOT_FOUND);
        }

        List<String> fieldValidationMessages = sessionValidator.validate(session);

        if (!fieldValidationMessages.isEmpty()) {
            return new DataResponse<>(session, HttpStatus.BAD_REQUEST, fieldValidationMessages);
        }

        session.setHirer(hirer.getData());

        return new DataResponse<>(sessionRepository.save(session), HttpStatus.OK);
    }

    public DataResponse<Session> setEmployeeToSession(long sessionId, long employeeId) {
        Employee employee = employeeService.get(employeeId).getData();
        Session session = sessionRepository.findById(sessionId).orElse(null);

        if (employee == null || session == null) {
            return new DataResponse<>(session, HttpStatus.NOT_FOUND);
        }

        session.setEmployee(employee);

        return new DataResponse<>(sessionRepository.save(session), HttpStatus.OK);
    }

    public DataResponse<Session> removeEmployeeFromSession(long sessionId) {
        Session session = sessionRepository.findById(sessionId).orElse(null);

        if (session == null) {
            return new DataResponse<>(null, HttpStatus.NOT_FOUND);
        }

        session.setEmployee(null);

        return new DataResponse<>(sessionRepository.save(session), HttpStatus.OK);
    }

    public List<Session> getAllEmployeeSessions(long employeeId) {
        return sessionRepository.getAllEmployeeSessions(employeeId);
    }

    public List<Session> getSessionsWithNoEmployee() {
        return sessionRepository.getSessionsWithNoEmployee();
    }
}
