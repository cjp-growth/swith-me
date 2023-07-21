package project.swithme.order.core.web.order.facade.validator;

import org.springframework.stereotype.Component;
import project.swithme.order.core.web.order.application.command.OrderCreateCommand;
import project.swithme.order.core.web.order.out.ProductClient;

@Component(value = "OrderValidation")
public class OrderValidator {

    private final ProductClient productClient;

    public OrderValidator(ProductClient productClient) {
        this.productClient = productClient;
    }

    public void validate(OrderCreateCommand orderCreateCommand) {
        productClient.validateProductDetails(
                orderCreateCommand.getStudyCafeId(),
                orderCreateCommand.getProductId(),
                orderCreateCommand.getProductPrice(),
                orderCreateCommand.getLockerId(),
                orderCreateCommand.getLockerPrice()
        );
    }
}
