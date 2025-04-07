package ao.multaplus.motoristVehicle.repository;

import ao.multaplus.motoristVehicle.entity.MotoristVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristVehicleRepository extends JpaRepository<MotoristVehicles, Long> {


}
