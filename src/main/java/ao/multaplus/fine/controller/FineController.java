package ao.multaplus.fine.controller;

import ao.multaplus.fine.dtos.FineDto;
import ao.multaplus.motorist.dtos.MotoristDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fine")
@Tag(name = "Fine", description = "Fine endpoints")
public class FineController {
    @PostMapping
    public void createMotorist(@RequestBody() FineDto FineDto) {

    }
}
