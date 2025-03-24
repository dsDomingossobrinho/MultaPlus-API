package ao.multaplus.state.service;

import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.StatusMensagem;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> editar(long id,StateSenderDto state){
       Optional<Status> status=repository.findById(id);
       if (status == null){
           sms.setMensagem("Status não encontrado");
           return new ResponseEntity<>(sms, HttpStatus.NOT_FOUND);
       }
       status.orElseThrow().setState(state.state());
       status.orElseThrow().setDescription(state.description());
       Status stat=status.get();
       if(status.orElseThrow().getState().equals("")){
            sms.setMensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setMensagem("Seu estado foi Salvo com Sucesso");
            repository.save(stat);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> cadastrar(StateSenderDto state){
        Status status=new Status();
        status.setState(state.state());
        status.setDescription(state.description());
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
    public Optional<Status> busca(long id){
        Optional<Status> status = repository.findById(id);
        return status;
    }
}
