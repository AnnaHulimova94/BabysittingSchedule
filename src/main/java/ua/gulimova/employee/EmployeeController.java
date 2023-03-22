package ua.gulimova.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> add(@RequestBody Employee employee) {
        EmployeeResponse employeeResponse = employeeService.add(employee);
        return new ResponseEntity<>(employeeResponse, employeeResponse.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable int id) {
        Employee employee = employeeService.get(id);
        HttpStatus httpStatus = employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(employee, httpStatus);
    }
}
