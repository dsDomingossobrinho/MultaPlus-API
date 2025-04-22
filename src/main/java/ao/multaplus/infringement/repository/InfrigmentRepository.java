package ao.multaplus.infringement.repository;

import ao.multaplus.infringement.entity.Infringements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfrigmentRepository extends JpaRepository<Infringements,Long> {
    Optional<Infringements> findById(Long id);
}
