package ua.gulimova.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.gulimova.auth.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from UserEntity where user_entity_username = ?1", nativeQuery = true)
    UserEntity getByUsername(String username);
}
