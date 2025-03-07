package ao.multaplus.action.repository;

import ao.multaplus.action.entity.Actions;
import ao.multaplus.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Actions,Long> {

    Optional<Actions> findById(Long id);

}
