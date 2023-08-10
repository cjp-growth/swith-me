package project.swithme.order.core.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.order.common.annotation.auth.LoginUser;
import project.swithme.order.common.response.ApiResponse;
import project.swithme.order.core.facade.OrderFacade;
import project.swithme.order.core.presentation.request.OrderCreateRequest;
import project.swithme.order.core.presentation.response.OrderCreateResponse;
import project.swithme.order.core.presentation.validator.OrderCreateValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderCreateAPI {

    private final OrderCreateValidator validator;
    private final OrderFacade orderFacade;

    @PostMapping
    public ApiResponse<OrderCreateResponse> order(
        @LoginUser StudyWithMeUser studyWithMeUser,
        @RequestBody OrderCreateRequest request
    ) {
        validator.validate(request);
        Long orderId = orderFacade.createOrder(studyWithMeUser, request.toCommand());
        OrderCreateResponse data = new OrderCreateResponse(orderId);
        return ApiResponse.created(data, HttpStatus.CREATED);
    }
}
