package ao.multaplus.fineInfringement.Service;

import ao.multaplus.fineInfringement.entity.FineInfringements;

import ao.multaplus.Infringement.entity.Infringements;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FineInfrigmentService {
    public ResponseEntity<List<FineInfringements>> searchInfrigmentsByFine(Long id);
    public ResponseEntity<?> savefineinfrigments(Long id, List<Infringements> infringements);
}
