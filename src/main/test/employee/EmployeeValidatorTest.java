import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.gulimova.employee.Employee;
import ua.gulimova.employee.EmployeeRepository;
import ua.gulimova.employee.EmployeeValidator;

import java.util.List;

import static org.mockito.Mockito.*;

class EmployeeValidatorTest {

    @InjectMocks
    private EmployeeValidator employeeValidator;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test phone format validation")
    @ParameterizedTest(name = "When phone = {0} then valid = {1}")
    @CsvSource({
            "+380503456432, true",
            "+38050453, false"
    })
    void test_validate_invalidPhoneFormat(String phone, boolean isValid) {
        Employee employee = new Employee("Anna", "Guli", phone);

        List<String> result = employeeValidator.validate(employee);

        if (isValid) {
            Assertions.assertEquals(0, result.size());
        } else {
            Assertions.assertEquals(1, result.size());
            Assertions.assertEquals("Phone number is not correct", result.get(0));
        }
    }

    @DisplayName("Test phone duplication")
    @ParameterizedTest
    @CsvSource({
            "+380503456432, true",
            "+380504765256, false"
    })
    void test_validate_duplicatePhone(String phone, boolean isDuplicated) {
        Employee employee = new Employee("Anna", "Guli", phone);
        Employee existingEmployee = isDuplicated ? new Employee() : null;

        doReturn(existingEmployee)
                .when(employeeRepository)
                .getByTelephoneNumber(anyString());

        List<String> result = employeeValidator.validate(employee);

        if (isDuplicated) {
            Assertions.assertEquals(1, result.size());
            Assertions.assertEquals("Phone number is already in use", result.get(0));
        } else {
            Assertions.assertEquals(0, result.size());
        }
    }
}
