package project.swithme.order.core.web.order.application.command;

import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.entity.OrderLine;
import project.swithme.order.core.domain.order.entity.PayType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static project.swithme.order.core.domain.order.entity.OrderLine.createOrderLine;

public record OrderCreateCommand(
        Long studyCafeId,
        Long productId,
        BigDecimal productPrice,
        Long lockerId,
        BigDecimal lockerPrice,
        PayType payType
) {

    public OrderCreateCommand {
        validateLocker(lockerId, lockerPrice);
    }

    private void validateLocker(
            Long lockerId,
            BigDecimal lockerPrice
    ) {
        if (lockerId != null && lockerPrice == null) {
            throw new IllegalArgumentException("올바른 락커 정보를 입력해주세요.");
        }
        if (lockerId == null && lockerPrice != null) {
            throw new IllegalArgumentException("올바른 락커 정보를 입력해주세요.");
        }
    }

    public Order toEntity(Long userId) {
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine ticket = createOrderLine(userId, studyCafeId, productId, productPrice);
        orderLines.add(ticket);

        if (lockerId != null && lockerPrice != null) {
            OrderLine locker = createOrderLine(userId, studyCafeId, lockerId, lockerPrice);
            orderLines.add(locker);
        }
        return new Order(userId, orderLines);
    }
}
