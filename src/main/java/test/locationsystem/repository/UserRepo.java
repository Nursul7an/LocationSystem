package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.locationsystem.model.User;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
