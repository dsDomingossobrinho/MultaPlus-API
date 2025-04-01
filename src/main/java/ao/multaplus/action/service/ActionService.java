package ao.multaplus.action.service;

import ao.multaplus.action.dto.ActionRequestDto;
import org.springframework.http.ResponseEntity;

public interface ActionService {

    void migration();
    ResponseEntity<?> getone(long id);
    ResponseEntity<?> getall();
    ResponseEntity<?> save(ActionRequestDto save);
    ResponseEntity<?> update(long id,ActionRequestDto save);
    ResponseEntity<?> delete(long id);
}
