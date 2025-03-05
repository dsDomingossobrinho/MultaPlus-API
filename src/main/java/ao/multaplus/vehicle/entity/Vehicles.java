package ao.multaplus.vehicle.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.status.entity.Status;
import ao.multaplus.typeVehicle.entity.TypeVehicles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vehicles extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a Registration")
    private String vehicles;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("vehicles")
    private Status state;

    @ManyToOne
    @JoinColumn(name = "type_vehicle_id")
    @JsonIgnoreProperties("vehicles")
    private TypeVehicles typeVehicles;

    @ManyToOne
    @JoinColumn(name = "motorist_id")
    @JsonIgnoreProperties("vehicles")
    private Motorists motorist;
}
