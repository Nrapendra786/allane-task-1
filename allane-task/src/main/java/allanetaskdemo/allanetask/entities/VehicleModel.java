package allanetaskdemo.allanetask.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;

@Entity
@Getter
@Setter
@Table(name = "vehicle_model")
public class VehicleModel extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Year year;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_brand_id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_vehicle_model_brand"))
    private VehicleBrand brand;
}
