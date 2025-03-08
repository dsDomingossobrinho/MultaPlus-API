package ao.multaplus.user.repository;

import ao.multaplus.status.entity.Status;
import ao.multaplus.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findById(Long id);

    Optional<Users> findByIdAndStatusId(Long id, Long status);
}
