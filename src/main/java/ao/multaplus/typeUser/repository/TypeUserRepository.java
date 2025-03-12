package ao.multaplus.typeUser.repository;

import ao.multaplus.statusPayment.entity.StatusPayment;
import ao.multaplus.typeUser.entity.TypeUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TypeUserRepository extends JpaRepository<TypeUsers,Long> {

    Optional<TypeUsers> findById(Long id);


}
