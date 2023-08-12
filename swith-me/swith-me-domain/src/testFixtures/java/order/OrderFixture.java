package order;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import project.swithme.domain.core.common.BaseInformation;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.OrderLine;
import project.swithme.domain.core.order.entity.OrderStatus;
import project.swithme.domain.core.order.entity.PayType;

public final class OrderFixture {

    private static final Long USER_ID = 1L;
    private static final Long STUDY_CAFE_ID = 1L;
    private static final Long STUDY_CAFE_TICKET_ID = 1L;
    private static final Long LOCKER_ID = 2L;
    private static final Long RESERVATION_ID = null;

    private static final String ORDER_TITLE = "스터디 카페 정기 이용권 및 락커 1달 이용권";
    private static final BigDecimal STUDY_CAFE_PRICE = BigDecimal.valueOf(100_000L);
    private static final BigDecimal LOCKER_PRICE = BigDecimal.valueOf(30_000L);

    private OrderFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static Order createOrder(OrderStatus orderStatus) {
        return new Order(
            null,
            USER_ID,
            RESERVATION_ID,
            UUID.randomUUID(),
            ORDER_TITLE,
            PayType.TOSS,
            orderStatus,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(USER_ID)
        );
    }

    public static Order createOrder(
        Long orderId,
        UUID uuid,
        OrderStatus orderStatus
    ) {
        return new Order(
            orderId,
            USER_ID,
            RESERVATION_ID,
            uuid,
            ORDER_TITLE,
            PayType.TOSS,
            orderStatus,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(1L)
        );
    }

    public static Order createOrder(UUID uuid) {
        return new Order(
            1L,
            USER_ID,
            RESERVATION_ID,
            uuid,
            ORDER_TITLE,
            PayType.TOSS,
            OrderStatus.PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(USER_ID)
        );
    }

    public static Order createOrder(
        Long orderId,
        UUID uuid
    ) {
        return new Order(
            orderId,
            USER_ID,
            RESERVATION_ID,
            uuid,
            ORDER_TITLE,
            PayType.TOSS,
            OrderStatus.PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(1L)
        );
    }

    public static OrderLine createOrderLine(Long id) {
        return new OrderLine(
            id,
            STUDY_CAFE_ID,
            STUDY_CAFE_TICKET_ID,
            new BigDecimal(100_000L),
            null,
            new BaseInformation(1L)
        );
    }

    private static List<OrderLine> createOrderLines() {
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine studyCafeTicket = OrderLine.createOrderLine(
            USER_ID,
            STUDY_CAFE_ID,
            STUDY_CAFE_TICKET_ID,
            STUDY_CAFE_PRICE
        );
        OrderLine lockerTicket = OrderLine.createOrderLine(
            USER_ID,
            STUDY_CAFE_ID,
            LOCKER_ID,
            LOCKER_PRICE
        );

        orderLines.add(studyCafeTicket);
        orderLines.add(lockerTicket);
        return orderLines;
    }
}
