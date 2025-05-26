package ao.multaplus.fine.repository;

import ao.multaplus.fine.dtos.FineResponse;
import ao.multaplus.fine.dtos.FineResponseDto;
import ao.multaplus.fine.entity.Fines;
import ao.multaplus.fine.service.FineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FineRepository extends JpaRepository<Fines, Long> {
    @Override
    Optional<Fines> findById(Long aLong);

    @Query("SELECT new ao.multaplus.fine.dtos.FineResponse(f.id, f.description,f.createdAt) " + "FROM " + "Fines f WHERE f" + ".motorists.bi = :identifier OR f.vehicles" + ".plateNumber = :identifier")
    Page<FineResponse> GetFines(@Param("identifier") String identifier, Pageable pageable
    );



     @Query("""
        SELECT f FROM Fines f
        LEFT JOIN FETCH f.motorists m
        LEFT JOIN FETCH f.users u
        LEFT JOIN FETCH f.vehicles v
        LEFT JOIN FETCH f.infringements i
        WHERE f.id = :fineIdentifier
    """)
    Optional<Fines> getFineDetails(@Param("fineIdentifier") Long fineIdentifier);

}
