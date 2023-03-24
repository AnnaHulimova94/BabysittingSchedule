package ua.gulimova.session;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.gulimova.employee.Employee;
import ua.gulimova.employee.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    private static final String MSG_EMPLOYEE_NOT_FOUND_MESSAGE = "Employee with such id is not found";

    private SessionRepository sessionRepository;

    private EmployeeService employeeService;

    private SessionValidator sessionValidator;

    public SessionService(SessionRepository sessionRepository, EmployeeService employeeService) {
        this.sessionRepository = sessionRepository;
        this.employeeService = employeeService;
        this.sessionValidator = new SessionValidator();
    }

    public SessionResponse add(SessionDTO sessionDTO) {
        Session session = sessionDTO.convertToSession();
        List<String> fieldValidationMessages = new ArrayList<>();

        Employee employee = employeeService.get(sessionDTO.getEmployeeId());

        if (employee == null) {
            fieldValidationMessages.add(MSG_EMPLOYEE_NOT_FOUND_MESSAGE);
        }

        fieldValidationMessages.addAll(sessionValidator.validate(session));

        if (!fieldValidationMessages.isEmpty()) {
            return new SessionResponse(session, HttpStatus.BAD_REQUEST, fieldValidationMessages);
        }

        session.setEmployee(employee);

        return new SessionResponse(session, HttpStatus.OK);
    }

    public List<Session> getAllEmployeeSessions(int employeeId) {
        return sessionRepository.getAllEmployeeSessions(employeeId);
    }
}
