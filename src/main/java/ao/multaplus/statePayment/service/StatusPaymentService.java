package ao.multaplus.statePayment.service;

import ao.multaplus.statePayment.dtos.StatusPaymentDTO;
import ao.multaplus.statePayment.dtos.StatusPaymentSaveDTO;
import org.springframework.http.ResponseEntity;

public interface StatusPaymentService {
    ResponseEntity<?> list();

    ResponseEntity<?> delete(long id);

    ResponseEntity<?> update(long id, StatusPaymentSaveDTO state);

    ResponseEntity<?> save(StatusPaymentSaveDTO state);

    ResponseEntity<?> findone(long id);
}
