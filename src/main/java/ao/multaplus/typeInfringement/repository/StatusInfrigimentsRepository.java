package ao.multaplus.typeInfringement.repository;

import ao.multaplus.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusInfrigimentsRepository extends JpaRepository<Status, Long> {
}
