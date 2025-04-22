package ao.multaplus.fine.repository;

import ao.multaplus.fine.entity.Fines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FineRepository extends JpaRepository<Fines,Long> {
    @Override
    Optional<Fines> findById(Long aLong);
}
