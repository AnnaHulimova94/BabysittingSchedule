package ua.gulimova.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    @Query(value = "select * from CustomUser where custom_user_username = ?1", nativeQuery = true)
    CustomUser getByUsername(String username);
}
