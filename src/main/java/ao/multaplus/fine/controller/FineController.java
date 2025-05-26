package ao.multaplus.fine.controller;

import ao.multaplus.fine.dtos.AddFineDto;
import ao.multaplus.fine.dtos.FineResponse;
import ao.multaplus.fine.dtos.FineResponseDto;
import ao.multaplus.fine.dtos.PageDto;
import ao.multaplus.fine.service.FineServiceImpl;
import ao.multaplus.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fine")
@Tag(name = "Fine", description = "Fine endpoints")
@RequiredArgsConstructor
@SecurityRequirement(name= SecurityConfigurations.SECURITY)
public class FineController {
    private final FineServiceImpl fineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMotorist(@RequestBody AddFineDto fineDetails) {
        fineService.AddFine(fineDetails);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<PageDto<FineResponse>> getFines(@PathVariable String identifier,
                                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                          @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(fineService.getFines(identifier, pageNumber, pageSize));
    }

    @GetMapping("/{fineIdentifier}/details")
    public ResponseEntity<FineResponseDto> getFines(@PathVariable Long fineIdentifier) {
        return ResponseEntity.ok(fineService.getFineDetails(fineIdentifier));
    }

}
