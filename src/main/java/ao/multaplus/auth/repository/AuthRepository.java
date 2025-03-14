package ao.multaplus.auth.repository;

import ao.multaplus.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findById(Long id);

    UserDetails findByEmail(String email);

    Optional<Auth> findByIdAndEmail(Long id, String email);
}
