package allanetaskdemo.allanetask.repositories;

import allanetaskdemo.allanetask.dtos.ContractDTO;
import allanetaskdemo.allanetask.entities.LeasingContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<LeasingContract,Long> {

    @Query(value = "SELECT vehicle_vehicle_id FROM Contract WHERE vehicle_vehicle_id = :vehicleId", nativeQuery = true)
    public Long getVehicleById(@Param("vehicleId") Long vehicleId);

    @Query("SELECT new allanetaskdemo.allanetask.dtos.ContractDTO(c.id, c.version, c.contractNumber, c.monthlyRate, " +
            "customer.id, customer.firstName, customer.lastName, " +
            "vehicle.id, vehicle.vin, vehicle.price, " +
            "brand.name, model.name, model.year) " +
            "FROM LeasingContract c " +
            "JOIN Customer customer on c.customer = customer " +
            "JOIN Vehicle vehicle on c.vehicle = vehicle " +
            "JOIN VehicleModel model on vehicle.model = model " +
            "JOIN VehicleBrand brand on model.brand = brand ")
    Page<ContractDTO> findContracts(Pageable pageRequest);

    @Query("SELECT new allanetaskdemo.allanetask.dtos.ContractDTO(c.id, c.version, c.contractNumber, c.monthlyRate, " +
            "customer.id, customer.firstName, customer.lastName, " +
            "vehicle.id, vehicle.vin, vehicle.price, " +
            "brand.name, model.name, model.year) " +
            "FROM LeasingContract c " +
            "JOIN Customer customer on c.customer = customer " +
            "JOIN Vehicle vehicle on c.vehicle = vehicle " +
            "JOIN VehicleModel model on vehicle.model = model " +
            "JOIN VehicleBrand brand on model.brand = brand " +
            "WHERE c.id = :id")
    Optional<ContractDTO> getContractDetails(Long id);

    Optional<LeasingContract> findByIdAndVersion(Long id, Integer version);

}
