package ao.multaplus.statusPayment.controller;

import ao.multaplus.statusPayment.dtos.StatusPaymentDTO;
import ao.multaplus.statusPayment.entity.StatusPayment;
import ao.multaplus.statusPayment.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statuspayment")
public class StatusPaymentController {

    @Autowired
    public Service statusPayment;

    @Operation(description = "List All StatusPayment", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping
    public ResponseEntity<?> listar(){
        return statusPayment.listar();
    }

    @Operation(description = "List All StatusPayment By ID", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/{id}")
    public ResponseEntity<?> listarid(@PathVariable long id){
        return statusPayment.buscar(id);
    }

    @Operation(description = "Save New Status", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody StatusPaymentDTO state){
        return statusPayment.cadastrar(state);
    }

    @Operation(description = "Update Status", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping
    public ResponseEntity<?> editar(@RequestBody StatusPaymentDTO state){
        return statusPayment.editar(state);
    }

    @Operation(description = "Delete StatusPayment", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return statusPayment.deletar(id);
    }
}
