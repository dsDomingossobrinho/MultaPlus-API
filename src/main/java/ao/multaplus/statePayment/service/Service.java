package ao.multaplus.statePayment.service;

import ao.multaplus.statePayment.entity.Mensagem;
import ao.multaplus.statePayment.entity.StatusPayment;
import ao.multaplus.statePayment.repository.StatusPaymentRepository;
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


    public ResponseEntity<?> listar(){
        List<StatusPayment> all = statusPayment.findAll();
        return new ResponseEntity<>( all, HttpStatus.OK);
    }



    public ResponseEntity<?> deletar(long id){
        StatusPayment tate = statusPayment.findById(id);
        statusPayment.delete(tate);
        sms.setmensagem("Estado do Pagamento Deletado Com Sucesso");
        return new ResponseEntity<>(sms, HttpStatus.OK);
    }



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


    public ResponseEntity<?> buscar(long id){
        StatusPayment status = statusPayment.findById(id);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
