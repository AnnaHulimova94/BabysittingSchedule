package ua.gulimova.session;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionResponse> add(@RequestBody SessionDTO sessionDTO) {
        return new ResponseEntity<>(sessionService.add(sessionDTO), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}/getAllSessions")
    public ResponseEntity<List<Session>> getAllEmployeeSessions(@PathVariable int employeeId) {
        return new ResponseEntity<>(sessionService.getAllEmployeeSessions(employeeId), HttpStatus.OK);
    }
}
