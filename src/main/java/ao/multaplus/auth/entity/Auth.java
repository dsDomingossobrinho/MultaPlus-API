package ao.multaplus.auth.entity;

import ao.multaplus.model.AbstractModel;
import ao.multaplus.role.entity.Roles;
import ao.multaplus.status.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auth extends AbstractModel {

    @Column(nullable = false, unique = true)
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Enter a email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Enter a password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("auths")
    private Status state;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("auths")
    private Roles role;
}
