package project.swithme.order.core.web.order.facade.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.order.core.web.order.application.command.OrderCreateCommand;
import project.swithme.order.core.web.order.out.ProductClient;

@RequiredArgsConstructor
@Component(value = "OrderValidation")
public class OrderValidator {

    private final ProductClient productClient;

    public void validate(OrderCreateCommand orderCreateCommand) {
        productClient.validateProductDetails(
                orderCreateCommand.studyCafeId(),
                orderCreateCommand.productId(),
                orderCreateCommand.productPrice(),
                orderCreateCommand.lockerId(),
                orderCreateCommand.lockerPrice()
        );
    }
}
