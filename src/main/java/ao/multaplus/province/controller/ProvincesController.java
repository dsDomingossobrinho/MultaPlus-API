package ao.multaplus.province.controller;

import ao.multaplus.province.DTO.ProvincesDTO;
import ao.multaplus.province.service.ProvinceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvincesController {
    private final ProvinceService service;

    public ProvincesController(ProvinceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProvincesDTO> create(@RequestBody @Valid ProvincesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvincesDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProvincesDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvincesDTO> update(@PathVariable Long id, @RequestBody @Valid ProvincesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
