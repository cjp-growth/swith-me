package project.swithme.payment.core.out.client.order;

import feign.FeignException;
import java.util.UUID;
import org.springframework.stereotype.Component;
import project.study.support.exception.ApiCallFailureException;
import project.study.support.exception.ErrorMessageParseException;
import project.swithme.domain.core.order.entity.OrderStatus;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.exception.OrderNotFoundException;
import project.swithme.payment.core.out.OrderQueryPort;
import project.swithme.payment.core.out.client.order.adapter.OrderAdapter;
import project.swithme.payment.core.out.client.order.adapter.response.OrderResponse;

@Component
public class OrderQueryClient implements OrderQueryPort {

    private final OrderAdapter orderAdapter;

    public OrderQueryClient(OrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
    }

    @Override
    public OrderValidationCommand findOrderByUniqueId(String orderUniqueId) {
        OrderResponse response;
        try {
            response = orderAdapter.findOrderByUniqueId(orderUniqueId);
            return convertToDomainLanguage(response);
        } catch (FeignException exception) {
            throw new OrderNotFoundException();
        } catch (IllegalArgumentException exception) {
            throw new ErrorMessageParseException(exception.getMessage());
        } catch (Exception exception) {
            throw new ApiCallFailureException(exception.getMessage());
        }
    }

    private OrderValidationCommand convertToDomainLanguage(OrderResponse response) {
        return new OrderValidationCommand(
            response.getOrderId(),
            response.getUserId(),
            UUID.fromString(response.getOrderUniqueId()),
            OrderStatus.findStatus(response.getOrderStatus()),
            response.getPrice()
        );
    }
}
