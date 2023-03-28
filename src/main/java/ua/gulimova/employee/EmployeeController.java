package ua.gulimova.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.gulimova.DataResponse;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<DataResponse<Employee>> add(@RequestBody Employee employee) {
        DataResponse<Employee> employeeResponse = employeeService.add(employee);
        return new ResponseEntity<>(employeeResponse, employeeResponse.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Employee>> get(@PathVariable long id) {
        DataResponse<Employee> employeeResponse = employeeService.get(id);
        return new ResponseEntity<>(employeeResponse, employeeResponse.getHttpStatus());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }
}
