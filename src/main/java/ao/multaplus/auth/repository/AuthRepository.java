package ao.multaplus.auth.repository;

import ao.multaplus.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findByid(Long id);
}
