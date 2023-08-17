package project.swithme.order.core.presentation.query.response;

import java.time.Instant;
import lombok.Getter;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.OrderStatus;
import project.swithme.domain.core.order.entity.PayType;

@Getter
public class OrderDetailResponse {

    private Long orderId;
    private String title;
    private OrderStatus orderStatus;
    private PayType payType;
    private Instant orderDate;

    public OrderDetailResponse(Order order) {
        this.orderId = order.getId();
        this.title = order.getTitle();
        this.orderStatus = order.getOrderStatus();
        this.payType = order.getPayType();
        this.orderDate = order.getCreateAt();
    }

    @Override
    public String toString() {
        return String.format(
            "orderId: %s, title: %s, orderStatus: %s, payType: %s, orderDate: %s",
            orderId, title, orderStatus, payType, orderDate
        );
    }
}
