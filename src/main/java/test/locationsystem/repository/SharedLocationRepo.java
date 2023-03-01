package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.locationsystem.model.entity.SharedLocation;
@Repository
public interface SharedLocationRepo extends JpaRepository<SharedLocation, Long> {
}
