package ao.multaplus.user.entity;

import ao.multaplus.gender.entity.Genders;
import ao.multaplus.province.entity.Provinces;
import ao.multaplus.status.entity.Status;
import ao.multaplus.model.AbstractModel;
import ao.multaplus.typeUser.entity.TypeUsers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Users extends AbstractModel {

    @Column(nullable = false)
    @NotBlank(message = "Enter a name")
    private String name;

    @Column(columnDefinition = "timestamp")
    private LocalDate dateBirth;

    @Column(nullable = false, unique = true)
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Enter a email")
    private String email;

    @Column(unique = true)
    private String telephone;

    @Column(nullable = false)
    @NotBlank(message = "Enter a senha")
    private String password;

    private String bi;

    private String img;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @JsonIgnoreProperties("users")
    private Genders genders;

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnoreProperties("users")
    private Status state;

    @ManyToOne
    @JoinColumn(name = "province_id")
    @JsonIgnoreProperties("users")
    private Provinces province;

    @ManyToOne
    @JoinColumn(name = "type_user_id")
    @JsonIgnoreProperties("users")
    private TypeUsers typeUsers;


}
