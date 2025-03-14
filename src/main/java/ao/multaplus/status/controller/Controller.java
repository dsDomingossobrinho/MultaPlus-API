package ao.multaplus.status.controller;

import ao.multaplus.status.entity.Status;
import ao.multaplus.status.service.StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    public StatusServiceImpl statusService;

    @GetMapping("listar")
    public ResponseEntity<?> listar(){
        return statusService.listar();
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<?> listarid(@PathVariable long id){
        return statusService.buscar(id);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Status state){
        return statusService.cadastrar(state);
    }

    @PutMapping("/cadastrar")
    public ResponseEntity<?> editar(@RequestBody Status state){
        return statusService.editar(state);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return statusService.deletar(id);
    }
}
