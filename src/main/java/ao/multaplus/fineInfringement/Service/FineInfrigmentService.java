package ao.multaplus.fineInfringement.Service;

import ao.multaplus.fineInfringement.entity.FineInfringements;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FineInfrigmentService {
    public ResponseEntity<List<FineInfringements>> searchInfrigmentsByFine(Long id);
}
