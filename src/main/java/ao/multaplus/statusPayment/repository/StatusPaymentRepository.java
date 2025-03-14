package ao.multaplus.statusPayment.repository;

import ao.multaplus.auth.entity.Auth;
import ao.multaplus.statusPayment.entity.StatusPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface StatusPaymentRepository extends JpaRepository<StatusPayment,Long> {

    Optional<StatusPayment> findById(Long id);

    StatusPayment findById(long id);

    Optional<Object> findByState(String eliminated);

    List<StatusPayment> findAll();
}
