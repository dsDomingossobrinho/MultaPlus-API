package ao.multaplus.Infringement.repository;

import ao.multaplus.Infringement.entity.Infringements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfringementsRepository extends JpaRepository<Infringements,Long> {
    boolean existsByName(String type);
    Optional<Infringements> findByName(String InfringementName);
}
