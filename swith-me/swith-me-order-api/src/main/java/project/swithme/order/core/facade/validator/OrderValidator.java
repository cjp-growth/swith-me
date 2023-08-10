package project.swithme.order.core.facade.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.order.core.application.command.OrderCreateCommand;
import project.swithme.order.core.out.ProductClient;

@RequiredArgsConstructor
@Component(value = "OrderValidation")
public class OrderValidator {

    private final ProductClient productClient;

    public void validate(OrderCreateCommand orderCreateCommand) {
        productClient.validateProductDetails(
            orderCreateCommand.studyCafeId(),
            orderCreateCommand.studyCafeTicketId(),
            orderCreateCommand.studyCafeTicketPrice(),
            orderCreateCommand.lockerId(),
            orderCreateCommand.lockerPrice()
        );
    }
}
