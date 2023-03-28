package ua.gulimova.hirer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.gulimova.DataResponse;

import java.util.List;

@RestController
@RequestMapping("/hirer")
public class HirerController {

    private HirerService hirerService;

    public HirerController(HirerService hirerService) {
        this.hirerService = hirerService;
    }

    @PostMapping
    public ResponseEntity<DataResponse<Hirer>> add(@RequestBody Hirer hirer) {
        DataResponse<Hirer> hirerResponse = hirerService.add(hirer);
        return new ResponseEntity<>(hirerResponse, hirerResponse.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Hirer>> get(@PathVariable long id) {
        DataResponse<Hirer> hirerResponse = hirerService.get(id);
        return new ResponseEntity<>(hirerResponse, hirerResponse.getHttpStatus());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hirer>> getAll() {
        return new ResponseEntity<>(hirerService.getAll(), HttpStatus.OK);
    }
}
