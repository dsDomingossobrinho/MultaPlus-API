package ao.multaplus.state.service;

import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface StatusService {


    void migration();

    public ResponseEntity<?> listar();

    public ResponseEntity<?> editar(long id,StateSenderDto state);

    public ResponseEntity<?> cadastrar(StateSenderDto state);

    public Optional<Status> busca(long id);
}
