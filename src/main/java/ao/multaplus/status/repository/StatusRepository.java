package ao.multaplus.status.repository;

import ao.multaplus.auth.entity.Auth;
import ao.multaplus.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {

    Optional<Status> findById(Long id);
}
