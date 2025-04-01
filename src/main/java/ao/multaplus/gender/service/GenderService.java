package ao.multaplus.gender.service;

import ao.multaplus.gender.dtos.GenderDto;
import ao.multaplus.gender.dtos.GenderDtoList;
import ao.multaplus.gender.entity.Genders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenderService {
    public void migrate();

    ResponseEntity<?> save(GenderDto gender);
    ResponseEntity<List<Genders>> list();
    ResponseEntity<?> update(long id, GenderDto genders);
    ResponseEntity<?> delete(long id);
    Optional<Genders> findone(long id);
}
