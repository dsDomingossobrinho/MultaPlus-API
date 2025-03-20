package ao.multaplus.state.service;

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

    //Listar os Estados
    public ResponseEntity<?> listar(){
        List<Status> all = repository.findAll();
        return new ResponseEntity<>( all, HttpStatus.OK);
    }


    //Deletar Os Estads
    public ResponseEntity<?> deletar(long id){
        Status tate = repository.findById(id).get();
        repository.delete(tate);
        sms.setMensagem("Estado Alterado Com Sucesso");
        return new ResponseEntity<>(sms, HttpStatus.OK);
    }


    //Actualizar os Estados
    public ResponseEntity<?> editar(Status state){
        if(state.getState().equals("")){
            sms.setMensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setMensagem("Seu estado foi Salvo com Sucesso");
            repository.save(state);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }


    //Salvar os Estados
    public ResponseEntity<?> cadastrar(Status state){
        if(state.getState().equals("")){
            sms.setMensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setMensagem("Seu estado foi Salvo com Sucesso");
            repository.save(state);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }


    //Listar Um Estado
    public ResponseEntity<?> buscar(long id){
        Status status = repository.findById(id).get();

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
