package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.locationsystem.model.Location;

public interface LocationRepo extends JpaRepository<Location,Long> {
}
