package ao.multaplus.payment.dtos;

import ao.multaplus.fine.entity.Fines;
import ao.multaplus.statePayment.entity.StatusPayment;

public record RequestPayment(
        StatusPayment statusPayment,
        Fines fine,
        float price
) {
}
