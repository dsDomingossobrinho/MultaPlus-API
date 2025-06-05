package ao.multaplus.payment.controller;

import ao.multaplus.payment.dtos.RequestPayment;
import ao.multaplus.payment.service.PaymentServiceImpl;
import ao.multaplus.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@SecurityRequirement(name= SecurityConfigurations.SECURITY)
public class PaymentController {
    private final PaymentServiceImpl paymentService;
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(description = "List All payment", tags = "payment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return paymentService.list();
    }

    @Operation(description = "List payment By ID", tags = "payment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @GetMapping("/list/{id}")
    public ResponseEntity<?> listone(@PathVariable long id){
        return paymentService.listone(id);
    }

    @Operation(description = "Save New payment", tags = "payment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid RequestPayment payment){
        return paymentService.save(payment);
    }

    @Operation(description = "Save New payment By Fine ID", tags = "payment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PostMapping("/save/{id}")
    public ResponseEntity<?> createpaymentbyfine(@PathVariable Long id){
        return paymentService.createnewpayment(id);
    }

    @Operation(description = "Update payment", tags = "payment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id,@RequestBody @Valid RequestPayment payment){
        return paymentService.update(id,payment);
    }

    @Operation(description = "Delete Payment", tags = "payment")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Success"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return paymentService.delete(id);
    }
}
