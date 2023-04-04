package allanetaskdemo.allanetask.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "vehicle_brand")
public class VehicleBrand extends  BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<VehicleModel> models = new HashSet<VehicleModel>();
}
