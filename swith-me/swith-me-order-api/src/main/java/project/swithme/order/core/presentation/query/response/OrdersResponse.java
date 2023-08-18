package project.swithme.order.core.presentation.query.response;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.utils.Cursor;

@Getter
public class OrdersResponse {

    private final boolean hasNext;
    private final List<OrderDetailResponse> orders;
    private final int size;

    public OrdersResponse(
        List<Order> orders,
        Cursor cursor
    ) {
        this.hasNext = orders.size() > cursor.getLimit();
        this.orders = convertToDto(orders, cursor);
        this.size = getDataSize(this.orders);
    }

    private List<OrderDetailResponse> convertToDto(
        List<Order> orders,
        Cursor cursor
    ) {
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }
        return orders.stream()
            .map(OrderDetailResponse::new)
            .limit(cursor.getLimit())
            .toList();
    }

    private int getDataSize(List<OrderDetailResponse> orders) {
        return orders.size();
    }

    @Override
    public String toString() {
        return String.format("hasNext: %s, orders: %s, size: %s", hasNext, orders, size);
    }
}
