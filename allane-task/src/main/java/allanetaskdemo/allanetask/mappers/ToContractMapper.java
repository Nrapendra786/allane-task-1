package allanetaskdemo.allanetask.mappers;

import allanetaskdemo.allanetask.dtos.ContractDTO;
import allanetaskdemo.allanetask.dtos.ContractDetailDTO;
import allanetaskdemo.allanetask.dtos.CustomerDTO;
import allanetaskdemo.allanetask.dtos.VehicleDTO;
import allanetaskdemo.allanetask.entities.LeasingContract;

public class ToContractMapper {

    public static ContractDetailDTO mapToContractOverviewDto(ContractDTO c) {
        return ContractDetailDTO.builder()
                .contract(ContractDTO.builder()
                        .id(c.getId())
                        .version(c.getVersion())
                        .contractNumber(c.getContractNumber())
                        .monthlyRate(c.getMonthlyRate())
                        .vehicleId(c.getVehicleId())
                        .customerId(c.getCustomerId())
                        .build())
                .customer(CustomerDTO.builder()
                        .id(c.getCustomerId())
                        .firstName(c.getCustomerFirstName())
                        .lastName(c.getCustomerLastName())
                        .build())
                .vehicle(VehicleDTO.builder()
                        .id(c.getVehicleId())
                        .brandName(c.getBrandName())
                        .modelName(c.getModelName())
                        .modelYear(c.getYear())
                        .vin(c.getVehicleVin())
                        .price(c.getVehiclePrice())
                        .build())
                .build();
    }

    public static LeasingContract mapFromDto(ContractDTO dto, LeasingContract contract) {
        contract.setContractNumber(dto.getContractNumber());
        contract.setMonthlyRate(dto.getMonthlyRate());
        return contract;
    }
}
