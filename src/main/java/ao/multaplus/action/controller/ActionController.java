package ao.multaplus.action.controller;

import ao.multaplus.action.dto.ActionRequestDto;
import ao.multaplus.action.service.ActionService;
import ao.multaplus.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/action")
@RequiredArgsConstructor
@SecurityRequirement(name= SecurityConfigurations.SECURITY)
public class ActionController {

    private final ActionService actionService;

    @Operation(description = "List an action", tags = "action")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getone(@PathVariable long id){
        return actionService.getone(id);
    }

    @Operation(description = "List All actions", tags = "action")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/list")
    public ResponseEntity<?> getall(){
        return actionService.getall();
    }

    @Operation(description = "Save an action", tags = "action")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ActionRequestDto save){
        return actionService.save(save);
    }

    @Operation(description = "Update an action", tags = "action")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody ActionRequestDto update){
        return actionService.update(id,update);
    }

    @Operation(description = "Delete an action", tags = "action")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return actionService.delete(id);
    }
}
