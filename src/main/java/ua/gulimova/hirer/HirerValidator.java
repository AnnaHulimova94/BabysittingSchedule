package ua.gulimova.hirer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.gulimova.DataResponse;
import ua.gulimova.Person.PersonValidator;

import java.util.List;

@Component
public class HirerValidator extends PersonValidator<Hirer> {

    private HirerRepository hirerRepository;

    public HirerValidator(HirerRepository hirerRepository) {
        this.hirerRepository = hirerRepository;
    }

    @Override
    public boolean isPhoneNumberUnique(String phoneNumber) {
        return hirerRepository.getByPhoneNumber(phoneNumber) == null;
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
