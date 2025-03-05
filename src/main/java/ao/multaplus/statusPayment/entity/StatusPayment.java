package ao.multaplus.statusPayment.entity;

import ao.multaplus.model.AbstractModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StatusPayment extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a Status")
    private String state;

    private String description;
}
