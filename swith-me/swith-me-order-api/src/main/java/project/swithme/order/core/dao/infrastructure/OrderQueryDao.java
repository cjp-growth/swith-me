package project.swithme.order.core.dao.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.QOrder;
import project.swithme.order.core.dao.OrderDao;

@Repository
@RequiredArgsConstructor
public class OrderQueryDao implements OrderDao {

    private static final QOrder order = QOrder.order;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Order> findOrderById(Long id) {
        return Optional.ofNullable(
            queryFactory.selectFrom(order)
                .where(
                    order.id.eq(id).and(
                        order.baseInformation.deleted.eq(Boolean.FALSE)
                    )
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<Order> findOrderById(UUID uniqueId) {
        return Optional.ofNullable(
            queryFactory.selectFrom(order)
                .where(
                    order.uniqueId.eq(uniqueId).and(
                        order.baseInformation.deleted.eq(Boolean.FALSE)
                    )
                )
                .fetchOne()
        );
    }
}
