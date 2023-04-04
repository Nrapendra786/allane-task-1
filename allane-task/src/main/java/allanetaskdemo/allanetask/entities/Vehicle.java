package allanetaskdemo.allanetask.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {

    @Column(nullable = false)
    private String vin;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_brand_id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "vehicle_model_fk"))
    private VehicleModel model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract",
            foreignKey = @ForeignKey(name = "vehicle_contract_fk"))
    private LeasingContract contract;
}
