package project.swithme.order.core.dao.infrastructure;

import static java.time.ZoneOffset.UTC;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.Instant;
import java.time.LocalDate;
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
    private static final LocalDate UTC_BASE_TIME = LocalDate.of(1972, 1, 1);
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
        Cursor cursor,
        LocalDate startDate,
        LocalDate endDate
    ) {
        if (cursor.pointRecentlyData()) {
            return queryFactory.selectFrom(order)
                .where(
                    order.userId.eq(studyWithMeUser.getUserId())
                        .and(order.baseInformation.deleted.eq(Boolean.FALSE))
                        .and(order.createAt.after(getStartDate(startDate))
                            .and(order.createAt.before(getEndDate(endDate)))
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
                        .and(order.baseInformation.deleted.eq(Boolean.FALSE))
                        .and(order.createAt.after(getStartDate(startDate))
                            .and(order.createAt.before(getEndDate(endDate)))
                        )
                    )
            )
            .limit(cursor.getLimit() + NEXT)
            .orderBy(order.id.desc())
            .fetch();
    }

    private Instant getStartDate(LocalDate date) {
        if (date == null) {
            return getInstant(UTC_BASE_TIME);
        }
        return getInstant(date);
    }

    private Instant getEndDate(LocalDate date) {
        if (date == null) {
            return getInstant(LocalDate.now());
        }
        return getInstant(date);
    }

    private Instant getInstant(LocalDate date) {
        return date.atStartOfDay(UTC)
            .toInstant();
    }
}
