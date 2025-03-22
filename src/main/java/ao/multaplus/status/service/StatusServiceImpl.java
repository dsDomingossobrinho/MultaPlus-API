package ao.multaplus.status.service;

import ao.multaplus.status.dtos.StatusDTO;
import ao.multaplus.status.entity.StatusMensagem;
import ao.multaplus.status.entity.Status;
import ao.multaplus.status.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {


    @Override
    @PostConstruct
    public void migration() {
        if (repository.count() == 0) {
            String[] array ={"active", "inactive", "eliminated"};

            for (int i = 0; i < array.length; i++) {
                Status state = new Status();
                state.setState(array[i]);
                repository.save(state);
            }
        }
    }

    @Autowired
    public StatusMensagem sms;

    @Autowired
    private StatusRepository repository;
    @Override
    public ResponseEntity<?> listar(){
        List<Status> all = repository.findAll();
        return new ResponseEntity<>( all, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editar(StatusDTO state){
        Status status=new Status();
        status.setState(state.state());
        status.setState(state.description());
        if(status.getState().equals("")){
            sms.setMensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setMensagem("Seu estado foi Salvo com Sucesso");
            repository.save(status);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> cadastrar(StatusDTO state){
        Status status=new Status();
        status.setState(state.state());
        status.setState(state.description());
        if(status.getState().equals("")){
            sms.setMensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setMensagem("Seu estado foi Salvo com Sucesso");
            repository.save(status);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public Status busca(long id){
        Status status = repository.findById(id);
        return status;
    }
}
