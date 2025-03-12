package ao.multaplus.typeInfringement.controller;

import ao.multaplus.typeInfringement.DTO.TypeInfringementsDTO;
import ao.multaplus.typeInfringement.service.TypeInfrigimentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-infrigiments")
public class TypeInfrigimentsController {
    private final TypeInfrigimentService service;


    public TypeInfrigimentsController(TypeInfrigimentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TypeInfringementsDTO> create(@RequestBody @Valid TypeInfringementsDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeInfringementsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TypeInfringementsDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeInfringementsDTO> update(@PathVariable Long id, @RequestBody @Valid TypeInfringementsDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
