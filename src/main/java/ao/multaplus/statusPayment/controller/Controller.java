package ao.multaplus.statusPayment.controller;

import ao.multaplus.statusPayment.entity.StatusPayment;
import ao.multaplus.statusPayment.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    public Service statusPayment;

    @GetMapping("listar")
    public ResponseEntity<?> listar(){
        return statusPayment.listar();
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<?> listarid(@PathVariable long id){
        return statusPayment.buscar(id);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody StatusPayment state){
        return statusPayment.cadastrar(state);
    }

    @PutMapping("/cadastrar")
    public ResponseEntity<?> editar(@RequestBody StatusPayment state){
        return statusPayment.editar(state);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id){
        return statusPayment.deletar(id);
    }
}
