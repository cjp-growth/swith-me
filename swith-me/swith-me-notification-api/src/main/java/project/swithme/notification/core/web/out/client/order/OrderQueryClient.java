package project.swithme.notification.core.web.out.client.order;

import feign.FeignException;
import org.springframework.stereotype.Component;
import project.study.support.exception.ApiCallFailureException;
import project.study.support.exception.ErrorMessageParseException;
import project.swithme.notification.core.web.exception.InvalidOrderException;
import project.swithme.notification.core.web.out.OrderQueryPort;
import project.swithme.notification.core.web.out.client.order.adapter.OrderAdapter;
import project.swithme.notification.core.web.out.client.order.adapter.response.OrderResponse;

@Component
public class OrderQueryClient implements OrderQueryPort {

    private final OrderAdapter orderAdapter;

    public OrderQueryClient(OrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
    }

    @Override
    public String findOrderTitleByUniqueId(String orderUniqueId) {
        try {
            OrderResponse response = orderAdapter.findOrderByUniqueId(orderUniqueId);
            return response.getTitle();
        } catch (FeignException exception) {
            throw new InvalidOrderException();
        } catch (IllegalArgumentException exception) {
            throw new ErrorMessageParseException(exception.getMessage());
        } catch (Exception exception) {
            throw new ApiCallFailureException(exception.getMessage());
        }
    }
}
