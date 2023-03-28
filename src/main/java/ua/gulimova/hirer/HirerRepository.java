package ua.gulimova.hirer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HirerRepository extends JpaRepository<Hirer, Long> {

    @Query(value = "select * from Hirer where hirer_phoneNumber = ?1", nativeQuery = true)
    Hirer getByPhoneNumber(String telephoneNumber);
}
