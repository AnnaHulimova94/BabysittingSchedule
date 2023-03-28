package ua.gulimova.hirer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.gulimova.DataResponse;
import ua.gulimova.Person.PersonValidator;

import java.util.List;

@Service
public class HirerService {

    private PersonValidator<Hirer> personValidator;

    private HirerRepository hirerRepository;

    public HirerService(HirerRepository hirerRepository, HirerValidator personValidator) {
        this.hirerRepository = hirerRepository;
        this.personValidator = personValidator;
    }

    public DataResponse<Hirer> add(Hirer hirer) {
        List<String> fieldValidationMessages = personValidator.validate(hirer);

        return fieldValidationMessages.isEmpty() ?
                new DataResponse<>(hirerRepository.save(hirer), HttpStatus.OK) :
                new DataResponse<>(hirer, HttpStatus.BAD_REQUEST, fieldValidationMessages);
    }

    public DataResponse<Hirer> get(long id) {
        Hirer hirer = hirerRepository.findById(id).orElse(null);

        if (hirer == null) {
            return new DataResponse<>(null, HttpStatus.NOT_FOUND);
        }

        return new DataResponse<>(hirer, HttpStatus.OK);
    }

    public List<Hirer> getAll() {
        return hirerRepository.findAll();
    }
}
