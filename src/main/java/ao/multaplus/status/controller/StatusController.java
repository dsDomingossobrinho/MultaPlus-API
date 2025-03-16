package ao.multaplus.status.controller;

import ao.multaplus.status.entity.Status;
import ao.multaplus.status.service.StatusServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller("statusController")
@RequestMapping("/status")
@RestController
public class StatusController {
    @Autowired
    public StatusServiceImpl statusService;

    @Operation(summary = "Listar", description = "Lista todos os Estados Registrados", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Dados Listados com Sucesso"))
    @GetMapping("listar")
    public ResponseEntity<?> listar(){
        return statusService.listar();
    }

    @Operation(summary = "Listar por ID", description = "Lista por ID todos os Estados Registrados", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Busca feita com Sucesso"))
    @GetMapping("listar/{id}")
    public ResponseEntity<?> listarid(@PathVariable long id){
        return statusService.buscar(id);
    }

    @Operation(summary = "Cadastrar", description = "Cadastra os Estados", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Estado Cadastrado com Sucesso"))
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Status state){
        return statusService.cadastrar(state);
    }

    @Operation(summary = "Alterar", description = "Altera Qualquer dado registrado sobre o Estado", tags = "status")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Dados Alterados com Sucesso"))
    @PutMapping("/cadastrar")
    public ResponseEntity<?> editar(@RequestBody Status state){
        return statusService.editar(state);
    }

}
