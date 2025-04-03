package ao.multaplus.typeIdentifier.service;

import ao.multaplus.typeIdentifier.dtos.TipeIdentifierDto;
import ao.multaplus.typeIdentifier.dtos.TipeidentifierSaveDTO;
import org.springframework.http.ResponseEntity;

public interface TypeIdentifierService {
    ResponseEntity<?> list();
    TipeIdentifierDto findone(long id);
    ResponseEntity<?> save(TipeidentifierSaveDTO saveDTO);
    ResponseEntity<?> update(long id,TipeidentifierSaveDTO saveDTO);
    ResponseEntity<?> delete(long id);
}
