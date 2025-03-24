package ao.multaplus.statePayment.service;

import ao.multaplus.statePayment.dtos.StatusPaymentDTO;
import org.springframework.http.ResponseEntity;

public interface StatusPaymentService {
    public ResponseEntity<?> listar();

    public ResponseEntity<?> deletar(long id);

    public ResponseEntity<?> editar(long id,StatusPaymentDTO state);

    public ResponseEntity<?> cadastrar(StatusPaymentDTO state);

    public ResponseEntity<?> buscar(long id);
}
