package project.swithme.order.core.web.order.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.swithme.order.common.annotation.auth.LoginUser;
import project.swithme.order.common.auth.StudyWithMeUser;
import project.swithme.order.common.response.ApiResponse;
import project.swithme.order.core.web.order.facade.OrderFacade;
import project.swithme.order.core.web.order.presentation.request.OrderRequest;
import project.swithme.order.core.web.order.presentation.response.OrderCreateResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderCreateAPI {

    private final OrderFacade orderFacade;

    @PostMapping
    public ApiResponse<OrderCreateResponse> order(
        @LoginUser StudyWithMeUser studyWithMeUser,
        @Valid @RequestBody OrderRequest request
    ) {
        Long orderId = orderFacade.createOrder(studyWithMeUser, request.toCommand());
        OrderCreateResponse data = new OrderCreateResponse(orderId);
        return ApiResponse.created(data, HttpStatus.CREATED);
    }
}
