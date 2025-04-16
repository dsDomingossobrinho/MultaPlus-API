package ao.multaplus.payment.service;

import ao.multaplus.payment.dtos.RequestPayment;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    public ResponseEntity<?> list();
    public ResponseEntity<?> listone(long id);
    public ResponseEntity<?> save(RequestPayment payment);
    public ResponseEntity<?> update(long id, RequestPayment payment);
    public ResponseEntity<?> delete(long id);
    public ResponseEntity<?> createnewpayment(long id);
}
