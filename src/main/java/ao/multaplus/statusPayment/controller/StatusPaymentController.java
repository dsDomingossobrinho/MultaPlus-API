package ao.multaplus.statusPayment.controller;

import ao.multaplus.statusPayment.entity.StatusPayment;
import ao.multaplus.statusPayment.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statuspayment")
public class StatusPaymentController {

    @Autowired
    public Service statusPayment;

    @Operation(summary = "Listar", description = "Lista todos os Estados dos pagamentos Registrados", tags = "statuspayment")
   @ApiResponses(@ApiResponse(responseCode = "200", description = "Dados Listados com Sucesso"))
    @GetMapping("listar")
    public ResponseEntity<?> listar(){
        return statusPayment.listar();
    }

    @Operation(summary = "Listar Por ID", description = "Lista por ID todos os Estados dos pagamentos Registrados", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca Realizada Com Sucesso"))
    @GetMapping("listar/{id}")
    public ResponseEntity<?> listarid(@PathVariable long id){
        return statusPayment.buscar(id);
    }

    @Operation(summary = "Cadastrar", description = "Cadastra os Estados dos pagamentos", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Dados Cadastrados com Sucesso"))
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody StatusPayment state){
        return statusPayment.cadastrar(state);
    }

    @Operation(summary = "Alterar", description = "Altera Qualquer dado registrado sobre o Estado do pagamento", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Dados Alterados com Sucesso"))
    @PutMapping("/cadastrar")
    public ResponseEntity<?> editar(@RequestBody StatusPayment state){
        return statusPayment.editar(state);
    }

    @Operation(summary = "Deletar", description = "Deleta Qualquer Estado do pagamento Registrado", tags = "statuspayment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Dados Deletados com Sucesso"))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return statusPayment.deletar(id);
    }
}
