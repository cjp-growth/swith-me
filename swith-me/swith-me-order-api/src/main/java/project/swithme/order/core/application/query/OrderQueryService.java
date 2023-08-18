package project.swithme.order.core.application.query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.support.exception.UnAuthorizedException;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.core.application.OrderQueryUseCase;
import project.swithme.order.core.dao.OrderDao;
import project.swithme.order.core.exception.OrderNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderQueryService implements OrderQueryUseCase {

    private final OrderDao orderDao;

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(
        StudyWithMeUser studyWithMeUser,
        Long orderId
    ) {
        Order findOrder = orderDao.findOrderById(orderId)
            .orElseThrow(OrderNotFoundException::new);

        if (!findOrder.isOrderer(studyWithMeUser.getUserId())) {
            throw new UnAuthorizedException();
        }
        return findOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findByUniqueId(String orderUniqueId) {
        return orderDao.findOrderById(UUID.fromString(orderUniqueId));
    }

    @Override
    public List<Order> findMyOrders(
        StudyWithMeUser studyWithMeUser,
        Cursor cursor
    ) {
        return orderDao.findMyOrders(studyWithMeUser, cursor);
    }
}
