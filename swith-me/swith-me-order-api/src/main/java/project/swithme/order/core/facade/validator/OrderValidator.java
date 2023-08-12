package project.swithme.order.core.facade.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.order.core.facade.command.OrderCreateCommand;
import project.swithme.order.core.out.ProductClient;

@RequiredArgsConstructor
@Component(value = "OrderValidation")
public class OrderValidator {

    private final ProductClient productClient;

    public void validate(OrderCreateCommand orderCreateCommand) {
        productClient.validateProductDetails(
            orderCreateCommand.getStudyCafeId(),
            orderCreateCommand.getStudyCafeTicketId(),
            orderCreateCommand.getStudyCafeTicketPrice(),
            orderCreateCommand.getLockerId(),
            orderCreateCommand.getLockerPrice()
        );
    }
}
