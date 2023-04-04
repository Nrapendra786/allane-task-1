package allanetaskdemo.allanetask.apis;


import allanetaskdemo.allanetask.dtos.ModelBrandDTO;
import allanetaskdemo.allanetask.dtos.VehicleDTO;
import allanetaskdemo.allanetask.dtos.VehicleDetailsDTO;
import allanetaskdemo.allanetask.entities.Vehicle;
import allanetaskdemo.allanetask.repositories.VehicleRepository;
import allanetaskdemo.allanetask.repositories.VehicleModelRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    private final VehicleModelRepository modelRepository;

    @GetMapping
    @ResponseBody
    Page<VehicleDTO> getVehicles(@RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size) {
        var pageable = PageRequest.of(page, size,  Sort.Direction.ASC, "id");
        return this.vehicleRepository.findVehicles(pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    VehicleDetailsDTO getVehicleDetails(@PathVariable("id") @NotNull Long id) {
        return this.vehicleRepository.getVehicleDetails(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findFree")
    @ResponseBody
    List<VehicleDTO> findFreeVehicles() {
        return this.vehicleRepository.findFree();
    }

    @GetMapping("/modelsAndBrands")
    @ResponseBody
    List<ModelBrandDTO> getModelsAndBrands() {
        return this.vehicleRepository.getModelsAndBrands();
    }

    @PostMapping
    @ResponseBody
    VehicleDetailsDTO createVehicle(@RequestBody @NotNull @Valid VehicleDetailsDTO vehicleDto) {
        var vehicle = this.mapFromDto(vehicleDto, new Vehicle());
        var model = this.modelRepository.findById(vehicleDto.getModelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Specified model is incorrect"));
        vehicle.setModel(model);
        vehicle = this.vehicleRepository.save(vehicle);
        return this.getVehicleDetails(vehicle.getId());
    }

    @PutMapping("/{id}")
    @ResponseBody
    VehicleDetailsDTO updateVehicle(@PathVariable("id") @NotNull Long id,
                                    @RequestBody @NotNull @Valid VehicleDetailsDTO vehicleDto) {
        var vehicle = this.vehicleRepository.findByIdAndVersion(id, vehicleDto.getVersion())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
        this.vehicleRepository.save(mapFromDto(vehicleDto, vehicle));
        return this.getVehicleDetails(id);
    }

    private Vehicle mapFromDto(VehicleDetailsDTO dto, Vehicle vehicle) {
        vehicle.setVin(dto.getVin());
        vehicle.setPrice(dto.getPrice());
        return vehicle;
    }
}
