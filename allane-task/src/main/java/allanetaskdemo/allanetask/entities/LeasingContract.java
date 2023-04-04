package allanetaskdemo.allanetask.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contract")
public class LeasingContract extends BaseEntity {

    @Column(nullable = false)
    private Long contractNumber;

    @Column(nullable = false)
    private BigDecimal monthlyRate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "contract_customer_fk"))
    private Customer customer;

    @OneToOne(optional = false, mappedBy = "contract")
    private Vehicle vehicle;

}
