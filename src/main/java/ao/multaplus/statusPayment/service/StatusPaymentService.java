package ao.multaplus.statusPayment.service;

import ao.multaplus.status.dtos.StatusDTO;
import ao.multaplus.status.entity.Status;
import ao.multaplus.statusPayment.dtos.StatusPaymentDTO;
import org.springframework.http.ResponseEntity;

public interface StatusPaymentService {
    public ResponseEntity<?> listar();

    public ResponseEntity<?> deletar(long id);

    public ResponseEntity<?> editar(StatusPaymentDTO state);

    public ResponseEntity<?> cadastrar(StatusPaymentDTO state);

    public ResponseEntity<?> buscar(long id);
}
