package test.locationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import test.locationsystem.model.entity.Location;
import test.locationsystem.model.entity.User;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location,Long> {
    @Query("SELECT b FROM Location b WHERE b.city LIKE %:value% OR b.country LIKE %:value% ")
    List<Location> findAllLocation(String value);
    List<Location> findAllByOwner(User owner);
}
