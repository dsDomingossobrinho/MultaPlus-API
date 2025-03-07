package ao.multaplus.action.service;

import ao.multaplus.action.entity.Actions;
import ao.multaplus.action.repository.ActionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
