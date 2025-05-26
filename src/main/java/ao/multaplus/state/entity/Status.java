package ao.multaplus.state.entity;

import ao.multaplus.model.AbstractModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Status extends AbstractModel {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Enter a Status")
    private String state;
    private String description;
}
