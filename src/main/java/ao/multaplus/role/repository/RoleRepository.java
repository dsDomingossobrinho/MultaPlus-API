package ao.multaplus.role.repository;

import ao.multaplus.role.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface RoleRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findById(Long id);

    Optional<Roles> findByRole(String role);

}
