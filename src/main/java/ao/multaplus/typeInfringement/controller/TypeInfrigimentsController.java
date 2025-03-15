package ao.multaplus.typeInfringement.controller;

import ao.multaplus.typeInfringement.dtos.TypeInfringementsResponsedtos;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsRequestdtos;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsResponsedtos;
import ao.multaplus.typeInfringement.service.TypeInfrigimentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-infrigiments")
@Tag(name = "Type Infrigiments", description = "Manage types Infringements")
public class TypeInfrigimentsController {

    private final TypeInfrigimentService service;

    public TypeInfrigimentsController(TypeInfrigimentService service) {
        this.service = service;
    }

    @Operation(summary = "Create a type infringents", description = "add a new type infringements to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "type infringements created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<TypeInfringementsResponsedtos> create(@RequestBody @Valid TypeInfringementsRequestdtos dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Get infringements by id ID", description = "return a specific infringement type based on the informed ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "type of infringement found successfully"),
            @ApiResponse(responseCode = "404", description = "type of infringement not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TypeInfringementsResponsedtos> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "List all type infringents", description = "return all type infringements")
    @ApiResponse(responseCode = "200", description = "list of all type infringements successfully")
    @GetMapping
    public ResponseEntity<List<TypeInfringementsResponsedtos>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "update type infringements by ID", description = "update the information of an existing type of infringement based on the informed ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "type of infringement updated successfully"),
            @ApiResponse(responseCode = "404", description = "type of infringement not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TypeInfringementsResponsedtos> update(@PathVariable Long id, @RequestBody @Valid TypeInfringementsRequestdtos dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete type Infringements by ID", description = "Remove the type of infringement from the system based on the informed ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "type of infringement deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Type of infringement not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

