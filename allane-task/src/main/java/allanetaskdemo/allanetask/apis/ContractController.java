package allanetaskdemo.allanetask.apis;

import allanetaskdemo.allanetask.dtos.ContractDTO;
import allanetaskdemo.allanetask.dtos.ContractDetailDTO;
import allanetaskdemo.allanetask.entities.LeasingContract;
import allanetaskdemo.allanetask.mappers.ToContractMapper;
import allanetaskdemo.allanetask.repositories.ContractRepository;
import allanetaskdemo.allanetask.repositories.CustomerRepository;
import allanetaskdemo.allanetask.repositories.VehicleRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    @GetMapping
    @ResponseBody
    Page<ContractDetailDTO> getContractDetails(@RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "10") Integer size) {
        var contracts = contractRepository.findContracts(
                PageRequest.of(page, size, Sort.Direction.ASC, "id"));
        return new PageImpl<>(contracts.stream()
                .map(ToContractMapper::mapToContractOverviewDto)
                .collect(Collectors.toList()),
                contracts.getPageable(), contracts.getTotalElements());
    }

    @GetMapping("/{id}")
    @ResponseBody
    ContractDetailDTO getContractDetail(@PathVariable("id") @NotNull Long id) {
        return this.contractRepository.getContractDetails(id)
                .map(ToContractMapper::mapToContractOverviewDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseBody
    ContractDetailDTO updateContract(@PathVariable("id") @NotNull Long id,
                                       @RequestBody @NotNull @Valid ContractDTO contractDto) {
        var contract = this.contractRepository.findByIdAndVersion(id, contractDto.getVersion())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
        contract = this.contractRepository.save(ToContractMapper.mapFromDto(contractDto, contract));
        return this.getContractDetail(contract.getId());
    }

    @PostMapping
    @ResponseBody
    ContractDetailDTO createContract(@RequestBody @NotNull @Valid ContractDTO contractDto) {
        // make sure the specified customer exists
        var customer = this.customerRepository.findById(contractDto.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "customer not found"));
        var vehicle = this.vehicleRepository.findById(contractDto.getVehicleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "vehicle not found"));

        // map and save contract
        var contract = ToContractMapper.mapFromDto(contractDto, new LeasingContract());
        contract.setCustomer(customer);
        contract = this.contractRepository.save(contract);
        vehicle.setContract(contract);
        this.vehicleRepository.save(vehicle);
      //  contract = this.contractRepository.save(contract);
        return this.getContractDetail(contract.getId());
    }
}
