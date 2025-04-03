package ao.multaplus.action.service;

import ao.multaplus.action.dto.ActionRequestDto;
import ao.multaplus.action.dto.ActionResponseDto;
import ao.multaplus.action.entity.Actions;
import ao.multaplus.action.repository.ActionRepository;
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
public class ActionServiceImpl implements ActionService {
    
    private final ActionRepository repository;
    @Override
    @PostConstruct
    public void migration() {
        if (repository.count() == 0) {
            String[] array ={"create", "update", "delete"};

            for (int i = 0; i < array.length; i++) {
                Actions action = new Actions();
                action.setAction(array[i]);
                repository.save(action);
            }
        }
    }

    @Override
    public ResponseEntity<?> getone(long id) {
        Optional<Actions> actionResponse=repository.findById(id);
        return new ResponseEntity<>(actionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getall() {
        List<Actions> actions=repository.findAll();
        return new ResponseEntity<>(actions,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(ActionRequestDto save) {
        Actions actions=new Actions();
        actions.setAction(save.action());
        actions.setDescription(save.description());
        repository.save(actions);
        return new ResponseEntity<>("Saved with success",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(long id, ActionRequestDto save) {
        Optional<Actions> actions=repository.findById(id);
        if (actions.isEmpty()){
            return new ResponseEntity<>("Cannot be empty",HttpStatus.BAD_REQUEST);
        }
        actions.orElseThrow().setId(id);
        actions.orElseThrow().setAction(save.action());
        actions.orElseThrow().setDescription(save.description());
        Actions actions1=new Actions();
        actions1=actions.get();
        repository.save(actions1);
        return new ResponseEntity<>("Updated with success",HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted with success",HttpStatus.OK);
    }
}
