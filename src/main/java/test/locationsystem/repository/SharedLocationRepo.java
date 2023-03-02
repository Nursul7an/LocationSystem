package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.locationsystem.model.entity.SharedLocation;
import test.locationsystem.model.entity.User;

import java.util.List;

@Repository
public interface SharedLocationRepo extends JpaRepository<SharedLocation, Long> {
    List<SharedLocation> findAllByReceiver(User receiver);
}
