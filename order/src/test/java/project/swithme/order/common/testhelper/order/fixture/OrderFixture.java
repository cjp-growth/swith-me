package project.swithme.order.common.testhelper.order.fixture;

import com.fasterxml.uuid.Generators;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import project.swithme.order.core.common.BaseInformation;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.entity.OrderLine;
import project.swithme.order.core.domain.order.entity.OrderStatus;
import project.swithme.order.core.domain.order.entity.PayType;

public final class OrderFixture {

    private static final Long USER_ID = 1L;
    private static final Long STUDY_CAFE_ID = 1L;
    private static final Long STUDY_CAFE_TICKET_ID = 1L;
    private static final Long LOCKER_ID = 2L;
    private static final Long RESERVATION_ID = null;

    private static final BigDecimal STUDY_CAFE_PRICE = BigDecimal.valueOf(100_000L);
    private static final BigDecimal LOCKER_PRICE = BigDecimal.valueOf(30_000L);

    private OrderFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static UUID createOrderUniqueId() {
        return Generators.timeBasedGenerator().generate();
    }

    public static Order createOrder(OrderStatus orderStatus) {
        return new Order(
            USER_ID,
            1L,
            RESERVATION_ID,
            Generators.timeBasedGenerator().generate(),
            PayType.TOSS,
            orderStatus,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(1L)
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
            PayType.TOSS,
            OrderStatus.PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(1L)
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
            PayType.TOSS,
            OrderStatus.PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            null,
            createOrderLines(),
            new BaseInformation(1L)
        );
    }

    private static List<OrderLine> createOrderLines() {
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine studyCafe = OrderLine.createOrderLine(
            USER_ID,
            STUDY_CAFE_ID,
            STUDY_CAFE_TICKET_ID,
            STUDY_CAFE_PRICE
        );
        OrderLine locker = OrderLine.createOrderLine(
            USER_ID,
            STUDY_CAFE_ID,
            LOCKER_ID,
            LOCKER_PRICE
        );

        orderLines.add(studyCafe);
        orderLines.add(locker);
        return orderLines;
    }
}
