package ua.gulimova.session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gulimova.DataResponse;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<DataResponse<Session>> add(@RequestBody SessionDTO sessionDTO) {
        DataResponse<Session> sessionResponse = sessionService.add(sessionDTO);
        return new ResponseEntity<>(sessionResponse, sessionResponse.getHttpStatus());
    }

    @GetMapping("/{employeeId}/get-all-sessions")
    public ResponseEntity<List<Session>> getAllEmployeeSessions(@PathVariable long employeeId) {
        return new ResponseEntity<>(sessionService.getAllEmployeeSessions(employeeId), HttpStatus.OK);
    }

    @GetMapping("/sessions-with-no-employee")
    public ResponseEntity<List<Session>> getSessionsWithNoEmployee() {
        return new ResponseEntity<>(sessionService.getSessionsWithNoEmployee(), HttpStatus.OK);
    }

    @PostMapping("set-employee-to-session")
    public ResponseEntity<DataResponse<Session>> setEmployeeToSession(@RequestParam long sessionId,
                                                                      @RequestParam long employeeId) {
        DataResponse<Session> session = sessionService.setEmployeeToSession(sessionId, employeeId);
        return new ResponseEntity<>(session, session.getHttpStatus());
    }

    @PostMapping("remove-employee-from-session")
    public ResponseEntity<DataResponse<Session>> removeEmployeeFromSession(@RequestParam long sessionId) {
        DataResponse<Session> session = sessionService.removeEmployeeFromSession(sessionId);
        return new ResponseEntity<>(session, session.getHttpStatus());
    }
}
