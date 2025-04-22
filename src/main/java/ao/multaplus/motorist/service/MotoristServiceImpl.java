package ao.multaplus.motorist.service;

import ao.multaplus.motorist.dtos.MotoristDto;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.motorist.repository.MotoristRepository;
import ao.multaplus.state.entity.Status;
import ao.multaplus.state.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MotoristServiceImpl implements MotoristService {
    private final MotoristRepository motoristRepository;
    private final StatusRepository statusRepository;

    @Override
    @Transactional
    public void registerMotorist(MotoristDto motoristDto) {
        try {
            Motorists motorist = Motorists.builder().
                    bi(motoristDto.bi()).
                    gender(null)
                    .dateBirth(motoristDto.dateBirth())
                    .state(getStatus(1L))
                    .telephone(motoristDto.telephone())
                    .name(motoristDto.name()).
                    build();
            motoristRepository.save(motorist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Motorists getMotorist(String motoristIdentifier) {
        return findMotorist(motoristIdentifier).orElseThrow(
                () -> new RuntimeException("Motorist not found"));
    }

    @Override
    @Transactional
    public void updateMotorist(String motoristIdentifier, MotoristDto motoristDto) {
        findMotorist(motoristIdentifier).ifPresentOrElse(motorists -> {
            boolean updated = false;
            if (
                    motoristDto.name() != null && !motoristDto.name().equals(
                            motorists.getName())) {
                motorists.setName(motoristDto.name());
                updated = true;
            }
            if (motoristDto.dateBirth() != null && !motoristDto.dateBirth().equals(motorists.getDateBirth())) {
                motorists.setDateBirth(motoristDto.dateBirth());
                updated = true;
            }
            if (motoristDto.telephone() != null && !motoristDto.telephone().equals(
                    motorists.getTelephone())) {
                motorists.setTelephone(motoristDto.telephone());
                updated = true;
            }
            if(motoristDto.bi() != null && !motoristDto.bi().equals(motorists.getBi())){
                motorists.setBi(motoristDto.bi());
                updated = true;
            }
            if (updated)
                motoristRepository.save(motorists);
        }, () -> {
            throw new RuntimeException("Motorist not found");
        });
    }

    @Override
    @Transactional
    public void deleteMotorist(String motoristIdentifier) {
        findMotorist(motoristIdentifier).ifPresentOrElse(motorists -> {
            if (motorists.getState().getId().equals(3L))
                throw new RuntimeException("Motorist already deleted");
            motorists.setState(getStatus(3L));
            motoristRepository.save(motorists);
        }, () -> {
            throw new RuntimeException("Motorist not found");
        });
    }

    @Override
    public Optional<Motorists> getBi(String bi) {
        // Dados fixos e predefinidos no formato BI:Nome,DataNascimento,Telefone,Genero,Estado;
        String predefinedData = """
        123456789LA045:João Silva,1990-05-15,+244912345678,M;
        987654321LB036:Maria Santos,1985-10-20,+244923456789,F;
        456789123LC056:Pedro Oliveira,1995-03-10,+244934567890,M;
        789123456LD023:Ana Ferreira,1992-07-18,+244945678901,F;
        321654987LE012:Bruno Costa,1988-11-25,+244956789012,M;
        654987321LF045:Carla Ramos,1993-04-22,+244967890123,F;
        852963741LG078:Daniel Pinto,1991-01-30,+244978901234,M;
        147258369LH074:Elisa Martins,1987-06-12,+244989012345,F;
        963852741LI063:Fernando Dias,1994-09-05,+244990123456,M;
        741852963LJ025:Gabriela Rocha,1996-12-14,+244901234567,F;
        369258147LK014:Hugo Mendes,1990-03-09,+244912345678,M;
        258147369LL085:Isabel Teixeira,1986-08-26,+244923456789,F;
        159357486LM036:Joana Almeida,1993-10-01,+244934567890,F;
        951753852LN023:Luís Fonseca,1992-05-19,+244945678901,M;
        357951456LO045:Marta Lopes,1989-02-23,+244956789012,F;
        654321987LP078:Nuno Pereira,1995-07-30,+244967890123,M;
        789654123LQ045:Olívia Cruz,1984-12-02,+244978901234,F;
        321987654LR065:Paulo Neves,1988-09-17,+244989012345,M;
        456123789LS048:Quésia Barros,1991-11-06,+244990123456,F;
        147369258LT074:Rafael Amaral,1996-01-20,+244901234567,M;
        963741852LU078:Sandra Viana,1990-06-15,+244912345678,F;
        852147963LV036:Tiago Couto,1994-04-11,+244923456789,M;
        741963852LW078:Úrsula Tavares,1987-10-28,+244934567890,F;
        369741258LX047:Vasco Salgado,1992-08-09,+244945678901,M;
        258963147LY052:Wanda Figueira,1985-03-14,+244956789012,F;
        159486357LZ036:Xavier Domingos,1993-12-24,+244967890123,M;
        951852753LA089:Yara Queirós,1991-07-07,+244978901234,F;
        357456159LB083:Zeca Branco,1989-09-11,+244989012345,M;
        654789321LC072:Aline Lopes,1996-10-05,+244990123456,F;
        789321654LD045:Bráulio Antunes,1990-02-02,+244901234567,M;
    """;

        String[] registros = predefinedData.split(";");
        for (String registro : registros) {
            String[] partes = registro.trim().split(":");
            if (partes.length < 2) continue; // ignore if format error

            if (partes[0].trim().equalsIgnoreCase(bi.trim())) {
                String[] data = partes[1].split(",");

                Motorists motorista = Motorists.builder()
                        .name(data[0])
                        .dateBirth(LocalDate.parse(data[1]))
                        .telephone(data[2])
                        .state(getStatus(1L))
                        .gender(null)
                        .bi(bi)
                        .build();
                return Optional.of(motorista);
            }
        }
        return Optional.empty(); // retur empty if not found
    }


    public Optional<Motorists> findMotorist(String motoristIdentifier) {
        return motoristRepository.findByBi(motoristIdentifier);
    }
    // remove this method and use the one from the StatusServiceImpl class when you are
    // done.
    private Status getStatus(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(
                () -> new RuntimeException("Status not found"));
    }


}
