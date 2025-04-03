package ao.multaplus.statePayment.service;

import ao.multaplus.statePayment.dtos.StatusPaymentDTO;
import ao.multaplus.statePayment.dtos.StatusPaymentSaveDTO;
import ao.multaplus.statePayment.entity.Mensagem;
import ao.multaplus.statePayment.entity.StatusPayment;
import ao.multaplus.statePayment.repository.StatusPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service
public class Service implements StatusPaymentService{

    @Autowired
    private Mensagem sms;

    @Autowired
    private StatusPaymentRepository statusPayment;


    @Override
    public ResponseEntity<?> list(){
        List<StatusPayment> all = statusPayment.findAll();
        return new ResponseEntity<>( all, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> delete(long id){
        StatusPayment tate = statusPayment.findById(id);
        statusPayment.delete(tate);
        sms.setmensagem("Status-Payment deleted with success");
        return new ResponseEntity<>(sms, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> update(long id,StatusPaymentSaveDTO state){
        StatusPayment stat= statusPayment.findById(id);
        if (stat == null){
            sms.setmensagem("Status-Payment not Found");
            return new ResponseEntity<>(sms, HttpStatus.NOT_FOUND);
        }
        stat.setId(id);
        stat.setState(state.state());
        stat.setDescription(state.description());
        if(stat.getState().equals("")){
            sms.setmensagem("Status-Payment cannot be empty");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setmensagem("Updated with success");
            statusPayment.save(stat);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> save(StatusPaymentSaveDTO state){
        StatusPayment status=new StatusPayment();
        status.setState(state.state());
        status.setDescription(state.description());
        if(status.getState().equals("")){
            sms.setmensagem("Status-Payment Cannot be empty");
            return new ResponseEntity<>(sms, HttpStatus.BAD_REQUEST);
        }else {
            sms.setmensagem("Saved with success");
            statusPayment.save(status);
            return new ResponseEntity<>(sms, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<?> findone(long id){
        StatusPayment status = statusPayment.findById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
