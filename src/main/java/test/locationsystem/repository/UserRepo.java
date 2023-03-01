package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.locationsystem.model.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsUserByEmail(String email);
}
