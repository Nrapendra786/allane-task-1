package allanetaskdemo.allanetask.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Data
public class ContractDetailDTO {
        private ContractDTO contract;

        private CustomerDTO customer;

        private VehicleDTO vehicle;
}
