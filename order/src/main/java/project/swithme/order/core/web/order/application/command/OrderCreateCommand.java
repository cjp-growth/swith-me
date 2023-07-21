package project.swithme.order.core.web.order.application.command;

import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.entity.OrderLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static project.swithme.order.core.domain.order.entity.OrderLine.createOrderLine;

public class OrderCreateCommand {

    private Long studyCafeId;
    private Long productId;
    private BigDecimal productPrice;
    private Long lockerId;
    private BigDecimal lockerPrice;

    public OrderCreateCommand(
            Long studyCafeId,
            Long productId,
            BigDecimal productPrice,
            Long lockerId,
            BigDecimal lockerPrice
    ) {
        validateLocker(lockerId, lockerPrice);
        this.studyCafeId = studyCafeId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.lockerId = lockerId;
        this.lockerPrice = lockerPrice;
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

    public Long getStudyCafeId() {
        return studyCafeId;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Long getLockerId() {
        return lockerId;
    }

    public BigDecimal getLockerPrice() {
        return lockerPrice;
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
