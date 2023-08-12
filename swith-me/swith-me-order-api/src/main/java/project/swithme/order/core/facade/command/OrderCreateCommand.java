package project.swithme.order.core.facade.command;

import static project.swithme.domain.core.order.entity.OrderLine.createOrderLine;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.OrderLine;
import project.swithme.domain.core.order.entity.PayType;

@Getter
public class OrderCreateCommand {

    private final Long studyCafeId;
    private final Long studyCafeTicketId;
    private final BigDecimal studyCafeTicketPrice;
    private final Long lockerId;
    private final BigDecimal lockerPrice;
    private final String title;
    private final PayType payType;

    public OrderCreateCommand(
        Long studyCafeId,
        Long studyCafeTicketId,
        BigDecimal studyCafeTicketPrice,
        Long lockerId,
        BigDecimal lockerPrice,
        String title,
        PayType payType
    ) {
        this.studyCafeId = studyCafeId;
        this.studyCafeTicketId = studyCafeTicketId;
        this.studyCafeTicketPrice = studyCafeTicketPrice;
        this.lockerId = lockerId;
        this.lockerPrice = lockerPrice;
        this.title = title;
        this.payType = payType;
    }

    public Order toEntity(Long userId) {
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine ticket = createOrderLine(
            userId,
            studyCafeId,
            studyCafeTicketId,
            studyCafeTicketPrice
        );
        orderLines.add(ticket);

        if (lockerId != null && lockerPrice != null) {
            OrderLine locker = createOrderLine(
                userId,
                studyCafeId,
                lockerId,
                lockerPrice
            );
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
