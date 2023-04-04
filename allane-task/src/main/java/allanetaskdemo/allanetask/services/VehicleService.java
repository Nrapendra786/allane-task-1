package allanetaskdemo.allanetask.services;

import allanetaskdemo.allanetask.entities.Vehicle;
import allanetaskdemo.allanetask.exceptions.ResourceNotFoundException;
import allanetaskdemo.allanetask.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAllVehicle() {
        return vehicleRepository.findAll();
    }

    public Vehicle findByVehicleId(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    public void deleteByVehicleId(Long id) {
         vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
         vehicleRepository.deleteById(id);
    }
}
