package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.locationsystem.model.entity.Location;

public interface LocationRepo extends JpaRepository<Location,Long> {
}
