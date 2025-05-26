package ao.multaplus.Infringement.controller;

import ao.multaplus.Infringement.dtos.InfringementsUpdateDto;
import ao.multaplus.Infringement.response.TypeInfringementsResponse;
import ao.multaplus.Infringement.service.InfringementService;
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
@Tag(name = "Type Infrigiments", description = "Manage types Infringement")
public class TypeInfrigimentsController {

    private final InfringementService service;

    public TypeInfrigimentsController(InfringementService service) {
        this.service = service;
    }

    @Operation(summary = "Create a name infringents", description = "add a new name infringements to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "name infringements created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<TypeInfringementsResponse> create(@RequestBody @Valid InfringementsUpdateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Get infringements by id ID", description = "return a specific infringement name based on the informed ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "name of infringement found successfully"),
            @ApiResponse(responseCode = "404", description = "name of infringement not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TypeInfringementsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "List all name infringents", description = "return all name infringements")
    @ApiResponse(responseCode = "200", description = "list of all name infringements successfully")
    @GetMapping
    public ResponseEntity<List<TypeInfringementsResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "update name infringements by ID", description = "update the information of an existing name of infringement based on the informed ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "name of infringement updated successfully"),
            @ApiResponse(responseCode = "404", description = "name of infringement not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TypeInfringementsResponse> update(@PathVariable Long id, @RequestBody @Valid InfringementsUpdateDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete name Infringement by ID", description = "Remove the name of infringement from the system based on the informed ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "name of infringement deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Type of infringement not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

