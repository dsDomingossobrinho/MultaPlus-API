package ao.multaplus.province.entity;

import ao.multaplus.model.AbstractModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provinces extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a Genders")
    private String province;
}