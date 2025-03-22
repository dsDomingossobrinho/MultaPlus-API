package ao.multaplus.typeInfringement.service;

import ao.multaplus.province.entity.Provinces;
import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsDto;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsUpdateDto;
import ao.multaplus.typeInfringement.entity.TypeInfringements;
import ao.multaplus.typeInfringement.repository.TypeInfrigimentsRepository;
import ao.multaplus.typeInfringement.response.TypeInfringementsResponse;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeInfrigimentServiceImpl implements TypeInfrigimentService {
    @Autowired
   private final TypeInfrigimentsRepository repository;
    @Autowired
    private final StatusRepository statusRepository;

    @Override
    public TypeInfringementsResponse create(TypeInfringementsUpdateDto requestdtos) {

        // Verifica se o tipo de infração já existe no repositório
        if (repository.existsByType(requestdtos.type())) {
            throw new RuntimeException("Infringement type already exists");
        }

        // Cria um novo objeto TypeInfringements
        TypeInfringements infringement = new TypeInfringements();

        // Busca o estado padrão
        Status state = statusRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default state not found"));

        // Define os valores no objeto de infração
        infringement.setType(requestdtos.type());
        infringement.setDescription(requestdtos.description());
        infringement.setPrice(Float.valueOf(requestdtos.price()));
        infringement.setState(state);

        // Salva a infração no repositório e retorna como DTO
        return toDto(repository.save(infringement));
    }

    @Override
    public TypeInfringementsResponse findById(Long id) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));
        return toDto(infringement);
    }

    @Override
    public List<TypeInfringementsResponse> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public TypeInfringementsResponse update(Long id, TypeInfringementsUpdateDto requestdtos) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));
        Status status = statusRepository.findById(requestdtos.state().id())
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));

        infringement.setType(infringement.getType());
        infringement.setDescription(infringement.getDescription());
        infringement.setPrice(infringement.getPrice());
        infringement.setState(status);

        return toDto(repository.save(infringement));
    }


    @Override
    public void delete(Long id) {
        TypeInfringements infringement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TypeInfringement not found"));

        // Alterar o estado para "eliminado"
        Status eliminatedStatus = statusRepository.findById(3L)
                .orElseThrow(() -> new EntityNotFoundException("Status 'eliminated' not found"));

        infringement.setState(eliminatedStatus);
        repository.save(infringement);
    }

   /* @Override
    @PostConstruct
    public void migration() {
        if (repository.count() == 0) {
            String[] types = {
                    "Speeding",
                    "Parking in prohibited areas",
                    "Driving without a license",
                    "Running a red light",
                    "Failure to comply with internal regulations",
                    "Misuse of company resources",
                    "Non-compliance with deadlines",
                    "Improper waste disposal",
                    "Pollution of water bodies",
                    "Illegal deforestation",
                    "Tax evasion",
                    "Failure to issue invoices",
                    "False or incomplete declarations",
                    "Public disorder",
                    "Minor theft",
                    "Light assaults"
            };
            String[] description = {
                    "Exceeding the speed limit",
                    " Parking vehicles in prohibited areas",
                    " Driving a vehicle without a valid license",
                    "Failing to stop at a red traffic light",
                    " Violating internal organizational rules",
                    " Misusing resources for personal purposes",
                    " Missing established deadlines",
                    " Disposing of waste improperly",
                    " Contaminating rivers",
                    " lakes, or other water bodies",
                    " Clearing forests without authorization",
                    " Avoiding taxes by falsifying or omitting financial information",
                    " Not issuing required invoices in commercial transactions",
                    " Providing false or incomplete information in legal documents",
                    " Causing public disturbances or disorder",
                    " Committing minor thefts of property",
                    " Engaging in light physical assaults without serious injury"
            };


            for (String infringementType : types) {
                TypeInfringements infringement = new TypeInfringements();
                infringement.setType(infringementType);
                repository.save(infringement);
            }
            for (String descriptionType : description) {
                TypeInfringements infringement = new TypeInfringements();
                infringement.setDescription(descriptionType);
                repository.save(infringement);
            }
        }
    }*/
   @Override
   @PostConstruct
   public void migration() {
       if (repository.count() == 0) {
           String[] types = {
                   "Speeding",
                   "Parking in prohibited areas",
                   "Driving without a license",
                   "Running a red light",
                   "Failure to comply with internal regulations",
                   "Misuse of company resources",
                   "Non-compliance with deadlines",
                   "Improper waste disposal",
                   "Pollution of water bodies",
                   "Illegal deforestation",
                   "Tax evasion",
                   "Failure to issue invoices",
                   "False or incomplete declarations",
                   "Public disorder",
                   "Minor theft",
                   "Light assaults"
           };
           String[] descriptions = {
                   "Exceeding the speed limit",
                   "Parking vehicles in prohibited areas",
                   "Driving a vehicle without a valid license",
                   "Failing to stop at a red traffic light",
                   "Violating internal organizational rules",
                   "Misusing resources for personal purposes",
                   "Missing established deadlines",
                   "Disposing of waste improperly",
                   "Contaminating rivers, lakes, or other water bodies",
                   "Clearing forests without authorization",
                   "Avoiding taxes by falsifying or omitting financial information",
                   "Not issuing required invoices in commercial transactions",
                   "Providing false or incomplete information in legal documents",
                   "Causing public disturbances or disorder",
                   "Committing minor thefts of property",
                   "Engaging in light physical assaults without serious injury"
           };

           for (int i = 0; i < types.length; i++) {
               TypeInfringements infringement = new TypeInfringements();
               infringement.setType(types[i]);
               infringement.setDescription(descriptions[i]);
               repository.save(infringement);
           }
       }
   }

    private TypeInfringementsResponse toDto(TypeInfringements infringement) {
        return new TypeInfringementsResponse(
                infringement.getId(),
                infringement.getType(),
                infringement.getDescription(),
                infringement.getPrice(),
                new StateSenderDto(infringement.getState().getId())
        );
    }
}
