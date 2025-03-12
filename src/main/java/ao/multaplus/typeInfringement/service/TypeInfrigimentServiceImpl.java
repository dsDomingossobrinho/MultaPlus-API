package ao.multaplus.typeInfringement.service;

import ao.multaplus.status.entity.Status;
import ao.multaplus.status.repository.StatusRepository;
import ao.multaplus.typeInfringement.DTO.TypeInfringementsDTO;
import ao.multaplus.typeInfringement.entity.TypeInfringements;
import ao.multaplus.typeInfringement.repository.TypeInfrigimentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeInfrigimentServiceImpl implements TypeInfrigimentService {
   private final TypeInfrigimentsRepository repository;
    private final StatusRepository statusRepository;

    public TypeInfrigimentServiceImpl(TypeInfrigimentsRepository repository, StatusRepository statusRepository) {
        this.repository = repository;
        this.statusRepository = statusRepository;
    }

    @Override
    public TypeInfringementsDTO create(TypeInfringementsDTO dto) {
        Status status = statusRepository.findById(dto.stateId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));

        TypeInfringements infringement = new TypeInfringements();
        infringement.setType(dto.type());
        infringement.setDescription(dto.description());
        infringement.setPrice(dto.price());
        infringement.setState(status);

        return toDto(repository.save(infringement));
    }

    @Override
    public TypeInfringementsDTO findById(Long id) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));
        return toDto(infringement);
    }

    @Override
    public List<TypeInfringementsDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public TypeInfringementsDTO update(Long id, TypeInfringementsDTO dto) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));
        Status status = statusRepository.findById(dto.stateId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));

        infringement.setType(dto.type());
        infringement.setDescription(dto.description());
        infringement.setPrice(dto.price());
        infringement.setState(status);

        return toDto(repository.save(infringement));
    }
    @Override
    public void delete(Long id) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));

        // Alterar o estado para "eliminado"
        Status eliminatedStatus = (Status) statusRepository.findByState("eliminated")
                .orElseThrow(() -> new EntityNotFoundException("Status 'eliminated' not found"));

        infringement.setState(eliminatedStatus);
        repository.save(infringement);
    }

    @Override
    public void migration() {
        if (statusRepository.count() == 0) {
            String[] statuses = {"active", "inactive", "eliminated"};
            for (String state : statuses) {
                Status status = new Status();
                status.setState(state);
                statusRepository.save(status);
            }
        }
    }

    private TypeInfringementsDTO toDto(TypeInfringements infringement) {
        return new TypeInfringementsDTO(
                infringement.getId(),
                infringement.getType(),
                infringement.getDescription(),
                infringement.getPrice(),
                infringement.getState() != null ? infringement.getState().getId() : null
        );
    }
}
