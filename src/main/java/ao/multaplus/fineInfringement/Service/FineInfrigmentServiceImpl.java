package ao.multaplus.fineInfringement.Service;

import ao.multaplus.fineInfringement.entity.FineInfringements;
import ao.multaplus.fineInfringement.repository.FineInfringementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineInfrigmentServiceImpl implements FineInfrigmentService {

    private final FineInfringementRepository fineInfringementRepository;
    public FineInfrigmentServiceImpl(FineInfringementRepository fineInfringementRepository) {
        this.fineInfringementRepository = fineInfringementRepository;
    }

    @Override
    public ResponseEntity<List<FineInfringements>> searchInfrigmentsByFine(Long id) {
        List<FineInfringements> fineInfringements=fineInfringementRepository.findByFineId(id);
        return ResponseEntity.ok(fineInfringements);
    }
}
