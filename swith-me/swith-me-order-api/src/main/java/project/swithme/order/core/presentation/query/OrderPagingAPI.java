package project.swithme.order.core.presentation.query;

import static project.study.support.codeandmessage.common.SuccessCodeAndMessage.OK;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.response.success.ApiResponse;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.annotation.auth.LoginUser;
import project.swithme.order.common.annotation.paging.CursorPageable;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.core.application.OrderQueryUseCase;
import project.swithme.order.core.presentation.query.response.OrdersResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderPagingAPI {

    private final OrderQueryUseCase orderQueryUseCase;

    @GetMapping("/my-orders")
    public ApiResponse<OrdersResponse> findMyOrders(
        @LoginUser StudyWithMeUser studyWithMeUser,
        @CursorPageable Cursor cursor
    ) {
        List<Order> data = orderQueryUseCase.findMyOrders(studyWithMeUser, cursor);
        OrdersResponse payLoad = new OrdersResponse(data, cursor);
        return ApiResponse.of(payLoad, OK);
    }
}
