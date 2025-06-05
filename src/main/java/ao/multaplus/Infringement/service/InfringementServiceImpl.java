package ao.multaplus.Infringement.service;

import ao.multaplus.Infringement.dtos.InfringementsUpdateDto;
import ao.multaplus.Infringement.entity.Infringements;
import ao.multaplus.Infringement.repository.InfringementsRepository;
import ao.multaplus.Infringement.response.TypeInfringementsResponse;
import ao.multaplus.exception.model.ResourceInConflictException;
import ao.multaplus.exception.model.ResourceNotFound;
import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.service.StatusServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfringementServiceImpl implements InfringementService {
    private final InfringementsRepository infringementsRepository;
    private final StatusServiceImpl statusService;

    @Override
    public TypeInfringementsResponse create(InfringementsUpdateDto infringementsDetails) {
        checkIfExists(infringementsDetails.name());
        Status state = statusService.getStatus(1L);
        Infringements infringement = Infringements.builder()
                .name(infringementsDetails.name())
                .price(infringementsDetails.price())
                .description(infringementsDetails.description())
                .state(state)
                .build();
        return toDto(infringementsRepository.save(infringement));
    }

    @Override
    public TypeInfringementsResponse findById(Long id) {
        Infringements infringement = getInfringement(id);
        return toDto(infringement);
    }

    @Override
    public List<TypeInfringementsResponse> findAll() {
        return infringementsRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public TypeInfringementsResponse update(Long id,
                                            InfringementsUpdateDto InfringementsDetails) {
        Infringements infringement = getInfringement(id);
        Status status = statusService.getStatus(InfringementsDetails.stateId());
        infringement.setName(infringement.getName());
        infringement.setDescription(infringement.getDescription());
        infringement.setPrice(infringement.getPrice());
        infringement.setState(status);
        return toDto(infringementsRepository.save(infringement));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Infringements infringement = getInfringement(id);
        Status eliminatedStatus = statusService.getStatus(3L);
        infringement.setState(eliminatedStatus);
        infringementsRepository.save(infringement);
    }

    public  List<Infringements> infringementsById(List<Long> ids) {

        List<Infringements> infringements = infringementsRepository.findAllById(ids);
        if (infringements.isEmpty())
            throw new ResourceNotFound("No infringements found for the provided IDs");
        return infringements;
    }

    @Override
    @PostConstruct
    public void migration() {
        if (infringementsRepository.count() == 0) {
            String[] types = {
                    "Excesso de velocidade",
                    "Estacionamento em áreas proibidas",
                    "Condução sem licença",
                    "Passagem no sinal vermelho",
                    "Incumprimento de regulamentos internos",
                    "Uso indevido de recursos da empresa",
                    "Não cumprimento de prazos",
                    "Descarte inadequado de resíduos",
                    "Poluição de corpos d'água",
                    "Desmatamento ilegal",
                    "Evasão fiscal",
                    "Não emissão de faturas",
                    "Declarações falsas ou incompletas",
                    "Desordem pública",
                    "Furto menor",
                    "Agressões leves"
            };
            String[] descriptions = {
                    "Ultrapassar o limite de velocidade permitido",
                    "Estacionar veículos em áreas proibidas",
                    "Conduzir um veículo sem licença válida",
                    "Não parar no sinal de trânsito vermelho",
                    "Violar regras organizacionais internas",
                    "Usar recursos para fins pessoais",
                    "Não cumprir prazos estabelecidos",
                    "Descartar resíduos de forma inadequada",
                    "Contaminar rios, lagos ou outros corpos d'água",
                    "Desmatar florestas sem autorização",
                    "Evitar impostos através de informações financeiras falsas ou omissas",
                    "Não emitir faturas necessárias em transações comerciais",
                    "Fornecer informações falsas ou incompletas em documentos legais",
                    "Causar distúrbios ou desordem pública",
                    "Cometer furtos menores de propriedade",
                    "Envolver-se em agressões físicas leves sem ferimentos graves"
            };
            float[] price = {
                    25000,
                    15000,
                    50000,
                    30000,
                    10000,
                    20000,
                    15000,
                    35000,
                    75000,
                    100000,
                    150000,
                    50000,
                    80000,
                    20000,
                    30000,
                    25000
            };

            for (int i = 0; i < types.length; i++) {
                Infringements infringement = new Infringements();
                infringement.setName(types[i]);
                infringement.setDescription(descriptions[i]);
                infringement.setPrice(price[i]);
                infringement.setState(statusService.getStatus(1L));
                System.out.println(infringementsRepository.save(infringement).getPrice());
            }
        }
    }

    @Override
    public Float getPrice(Long id) {
        Optional<Infringements> typeInfringements = infringementsRepository.findById(id);
        return typeInfringements.orElseThrow().getPrice();
    }

    private TypeInfringementsResponse toDto(Infringements infringement) {
        return new TypeInfringementsResponse(
                infringement.getId(),
                infringement.getName(),
                infringement.getDescription(),
                infringement.getPrice(),
                new StateSenderDto(infringement.getState().getId())
        );
    }

    @Override
    public Infringements getInfringement(String infringementName) {
        return infringementsRepository.findByName(infringementName).orElseThrow(
                () -> new ResourceNotFound("TypeInfringement not found"));
    }

    public Infringements getInfringement(Long infringementId) {
        return infringementsRepository.findById(infringementId).orElseThrow(
                () -> new ResourceNotFound(
                        "TypeInfringement not found"));
    }

    public void checkIfExists(String type) {
        if (infringementsRepository.existsByName(type)) {
            throw new ResourceInConflictException("TypeInfringement already exists");
        }
    }
}
