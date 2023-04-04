package allanetaskdemo.allanetask.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;

@AllArgsConstructor
@Getter
public class VehicleDetailsDTO {

    private final Long id;

    private final Integer version;
    @Size(min = 17, max = 17)
    private final String vin;
    @NotNull
    private final BigDecimal price;
    private final Long brandId;
    private final String brandName;

    @NotNull
    private final Long modelId;
    private final String modelName;
    private final Year modelYear;

    private final Long contractId;
    private final Long contractNumber;
}
