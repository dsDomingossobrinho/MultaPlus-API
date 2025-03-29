package ao.multaplus.state.service;

import ao.multaplus.state.dtos.StateDto;
import ao.multaplus.state.dtos.StateSaveDto;
import ao.multaplus.state.entity.Status;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface StatusService {


    void migration();

    ResponseEntity<?> list();

    ResponseEntity<?> update(long id, StateSaveDto state);

    ResponseEntity<?> save(StateSaveDto state);

    Optional<Status> findone(long id);
    ResponseEntity<?> delete(long id);
}
