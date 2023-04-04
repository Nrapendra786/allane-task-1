package allanetaskdemo.allanetask.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
public class CustomerDTO {

    private final Long id;

    @NotNull
    private final Integer version;

    @NotNull
    @Size(max = 255)
    private final String firstName;

    @NotNull
    @Size(max = 255)
    private final String lastName;

    @NotNull
    @Past
    private final LocalDate birthDate;
}
