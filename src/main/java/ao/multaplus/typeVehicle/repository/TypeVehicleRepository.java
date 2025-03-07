package ao.multaplus.typeVehicle.repository;

import ao.multaplus.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface TypeVehicleRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findByid(Long id);

    UserDetails findByemail(String email);
}
