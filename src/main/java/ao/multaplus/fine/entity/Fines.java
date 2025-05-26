package ao.multaplus.fine.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.payment.entity.Payments;
import ao.multaplus.state.entity.Status;
import ao.multaplus.Infringement.entity.Infringements;
import ao.multaplus.user.entity.Users;
import ao.multaplus.vehicle.entity.Vehicles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Fines extends AbstractModel {
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("fines")
    private Users users;
    @ManyToOne
    private Vehicles vehicles;
    @ManyToOne
    private Motorists motorists;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private Status state;
    @ManyToMany
    private List<Infringements> infringements;
    private Integer daysTOPay;
    @OneToMany
    private List<Payments> payments = new ArrayList<>();
}
