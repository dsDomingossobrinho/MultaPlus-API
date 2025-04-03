package ao.multaplus.typeIdentifier.controller;

import ao.multaplus.typeIdentifier.dtos.TipeIdentifierDto;
import ao.multaplus.typeIdentifier.dtos.TipeidentifierSaveDTO;
import ao.multaplus.typeIdentifier.entity.TypeIdentifiers;
import ao.multaplus.typeIdentifier.service.TypeIdentifierServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/typeidentifier")
@RestController
public class TypeIdentifierController {
    private final TypeIdentifierServiceImpl identifierService;
    public TypeIdentifierController(TypeIdentifierServiceImpl identifierService){
        this.identifierService=identifierService;
    }

    @Operation(description = "List All typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return identifierService.list();
    }

    @Operation(description = "Find a typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/list/{id}")
    public TipeIdentifierDto listbyid(@PathVariable long id){
        return identifierService.findone(id);
    }

    @Operation(description = "Save typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TipeidentifierSaveDTO saveDTO){
        return identifierService.save(saveDTO);
    }

    @Operation(description = "Edit typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody TipeidentifierSaveDTO saveDTO){
        return identifierService.update(id,saveDTO);
    }

    @Operation(description = "Delete typeidentifier", tags = "typeidentifier")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
      return identifierService.delete(id);
    }
}
