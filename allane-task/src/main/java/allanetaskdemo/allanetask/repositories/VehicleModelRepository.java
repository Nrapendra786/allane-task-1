package allanetaskdemo.allanetask.repositories;

import allanetaskdemo.allanetask.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleModelRepository extends JpaRepository<VehicleModel,Long> {
}
