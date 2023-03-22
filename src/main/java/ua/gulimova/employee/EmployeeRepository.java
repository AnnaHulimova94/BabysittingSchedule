package ua.gulimova.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select * from Employee where employee_phoneNumber = ?1", nativeQuery = true)
    Employee getByTelephoneNumber(String telephoneNumber);
}
