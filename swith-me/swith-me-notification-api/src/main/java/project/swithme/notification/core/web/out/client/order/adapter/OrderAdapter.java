package project.swithme.notification.core.web.out.client.order.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.swithme.notification.common.configuration.feign.FeignJsonConfiguration;
import project.swithme.notification.core.web.out.client.order.adapter.response.OrderResponse;

@FeignClient(
    name = "orderClient",
    url = "${orders.url}",
    configuration = {FeignJsonConfiguration.class}
)
public interface OrderAdapter {

    @GetMapping(value = "/api/orders/{orderUniqueId}")
    OrderResponse findOrderByUniqueId(@PathVariable("orderUniqueId") String orderUniqueId);
}
