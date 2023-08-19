package project.swithme.order.core.dao.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.QOrder;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.core.dao.OrderDao;

@Repository
@RequiredArgsConstructor
public class OrderQueryDao implements OrderDao {

    private static final int NEXT = 1;
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

    @Override
    public List<Order> findMyOrders(
        StudyWithMeUser studyWithMeUser,
        Cursor cursor
    ) {
        if (cursor.pointRecentlyData()) {
            return queryFactory.selectFrom(order)
                .where(
                    order.userId.eq(studyWithMeUser.getUserId())
                        .and(order.baseInformation.deleted.eq(Boolean.FALSE)
                        )
                )
                .limit(cursor.getLimit() + NEXT)
                .orderBy(order.id.desc())
                .fetch();
        }
        return queryFactory.selectFrom(order)
            .where(
                order.id.lt(cursor.getIndex())
                    .and(order.userId.eq(studyWithMeUser.getUserId())
                        .and(order.baseInformation.deleted.eq(Boolean.FALSE)
                        )
                    )
            )
            .limit(cursor.getLimit() + NEXT)
            .orderBy(order.id.desc())
            .fetch();
    }
}
