package ua.gulimova.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query(value = "select * from Session where employee_id = ?1", nativeQuery = true)
    List<Session> getAllEmployeeSessions(int employeeId);
}
