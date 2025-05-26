package ao.multaplus.Infringement.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.state.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Infringements extends AbstractModel {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter a name for the infringement")
    private String name;
    private String description;
    private Float price;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private Status state;
}
