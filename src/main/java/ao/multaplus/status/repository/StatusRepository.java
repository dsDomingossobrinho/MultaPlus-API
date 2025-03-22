package ao.multaplus.status.repository;

import ao.multaplus.auth.entity.Auth;
import ao.multaplus.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {
    Optional<Status> findById(Long id);

    Status findById(long id);

    Optional<Object> findByState(String eliminated);

    List<Status> findAll();
}
