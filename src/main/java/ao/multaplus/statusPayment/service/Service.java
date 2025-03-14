package ao.multaplus.statusPayment.service;

import ao.multaplus.statusPayment.entity.Mensagem;
import ao.multaplus.statusPayment.entity.StatusPayment;
import ao.multaplus.statusPayment.repository.StatusPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Mensagem sms;

    @Autowired
    private StatusPaymentRepository statusPayment;

    //Listar os Estados
    public ResponseEntity<?> listar(){
        List<StatusPayment> all = statusPayment.findAll();
        return new ResponseEntity<>( all, HttpStatus.OK);
    }


    //Deletar Os Estads
    public ResponseEntity<?> deletar(long id){
        StatusPayment tate = statusPayment.findById(id);
        statusPayment.delete(tate);
        sms.setmensagem("Estado Alterado Com Sucesso");
        return new ResponseEntity<>(sms, HttpStatus.OK);
    }


    //Actualizar os Estados
    public ResponseEntity<?> editar(StatusPayment state){
        if(state.getClass().equals("")){
            sms.setmensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setmensagem("Seu estado foi Salvo com Sucesso");
            statusPayment.save(state);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }


    //Salvar os Estados
    public ResponseEntity<?> cadastrar(StatusPayment state){
        if(state.getClass().equals("")){
            sms.setmensagem("O estado não pode estar vazio");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setmensagem("Seu estado foi Salvo com Sucesso");
            statusPayment.save(state);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }


    //Listar Um Estado
    public ResponseEntity<?> buscar(long id){
        StatusPayment status = statusPayment.findById(id);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
