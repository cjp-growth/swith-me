package project.swithme.order.core.web.order.application.command;

import static project.swithme.order.core.domain.order.entity.OrderLine.createOrderLine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.entity.OrderLine;
import project.swithme.order.core.domain.order.entity.PayType;

public record OrderCreateCommand(
    Long studyCafeId,
    Long studyCafeTicketId,
    BigDecimal studyCafeTicketPrice,
    Long lockerId,
    BigDecimal lockerPrice,
    String title,
    PayType payType
) {

    public Order toEntity(Long userId) {
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine ticket = createOrderLine(userId, studyCafeId, studyCafeTicketId,
            studyCafeTicketPrice);
        orderLines.add(ticket);

        if (lockerId != null && lockerPrice != null) {
            OrderLine locker = createOrderLine(userId, studyCafeId, lockerId, lockerPrice);
            orderLines.add(locker);
        }
        return new Order(userId, title, payType, orderLines);
    }

    @Override
    public String toString() {
        return String.format(
            "studyCafeId: %s, studyCafeTicketId: %s, studyCafeTicketPrice: %s, lockerId: %s, lockerPrice: %s, title: %s, payType: %s",
            studyCafeId, studyCafeTicketId, studyCafeTicketPrice, lockerId, lockerPrice, title,
            payType
        );
    }
}
