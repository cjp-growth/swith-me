package project.swithme.order.core.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.core.application.OrderCommandUseCase;
import project.swithme.order.core.facade.command.OrderCreateCommand;
import project.swithme.order.core.facade.validator.OrderValidator;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderValidator orderValidator;
    private final OrderCommandUseCase orderCommandUseCase;

    // TODO. 상품 매핑이 끝난 후 Validation 추가
    public Long createOrder(
        StudyWithMeUser switmeUser,
        OrderCreateCommand orderCreateCommand
    ) {
        orderValidator.validate(orderCreateCommand);
        Order order = orderCreateCommand.toEntity(switmeUser.getUserId());
        return orderCommandUseCase.save(order);
    }
}
