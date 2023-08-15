package project.swithme.order.core.presentation.query;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.codeandmessage.common.SuccessCodeAndMessage;
import project.study.support.response.success.ApiResponse;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.annotation.auth.LoginUser;
import project.swithme.order.core.application.OrderQueryUseCase;
import project.swithme.order.core.presentation.query.response.OrderDetailResponse;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderDetailAPI {

    private final OrderQueryUseCase orderQueryUseCase;

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDetailResponse> searchOrderDetail(
        @LoginUser StudyWithMeUser studyWithMeUser,
        @PathVariable Long orderId
    ) {
        Order order = orderQueryUseCase.findOrderById(studyWithMeUser, orderId);
        OrderDetailResponse data = new OrderDetailResponse(order);
        return ApiResponse.of(data, SuccessCodeAndMessage.OK);
    }
}
