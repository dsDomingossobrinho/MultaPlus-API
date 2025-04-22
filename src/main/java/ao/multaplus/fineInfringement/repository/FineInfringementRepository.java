package ao.multaplus.fineInfringement.repository;

import ao.multaplus.fineInfringement.entity.FineInfringements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineInfringementRepository extends JpaRepository<FineInfringements,Long> {
    List<FineInfringements> findByFineId(Long id);
}
