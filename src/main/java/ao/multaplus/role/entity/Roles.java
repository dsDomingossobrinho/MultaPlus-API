package ao.multaplus.role.entity;

import ao.multaplus.model.AbstractModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Roles extends AbstractModel {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter a Roles")
    private String role;

    private String description;
}
