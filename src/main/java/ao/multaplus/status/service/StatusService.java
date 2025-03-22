package ao.multaplus.status.service;

import ao.multaplus.status.dtos.StatusDTO;
import ao.multaplus.status.entity.Status;
import org.springframework.http.ResponseEntity;

public interface StatusService {

    void migration();

    public ResponseEntity<?> listar();

    public ResponseEntity<?> editar(StatusDTO state);

    public ResponseEntity<?> cadastrar(StatusDTO state);

    public Status busca(long id);
}
