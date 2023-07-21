package project.swithme.order.core.web.order.facade;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.order.common.auth.StudyWithMeUser;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.web.order.application.OrderUseCase;
import project.swithme.order.core.web.order.application.command.OrderCreateCommand;
import project.swithme.order.core.web.order.facade.validator.OrderValidator;

@Component
public class OrderFacade {

    private final OrderValidator orderValidator;
    private final OrderUseCase orderUseCase;

    public OrderFacade(
            OrderValidator orderValidator,
            OrderUseCase orderUseCase
    ) {
        this.orderValidator = orderValidator;
        this.orderUseCase = orderUseCase;
    }

    // TODO. 상품 매핑이 끝난 후 Validation 추가
    @Transactional
    public Long createOrder(
            StudyWithMeUser switmeUser,
            OrderCreateCommand orderCreateCommand
    ) {
        orderValidator.validate(orderCreateCommand);
        Order order = orderCreateCommand.toEntity(switmeUser.getUserId());
        return orderUseCase.save(order);
    }
}
