package project.swithme.order.core.presentation.command.validator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import project.study.support.exception.InvalidParameterException;
import project.study.support.log.Field;
import project.swithme.domain.core.order.entity.PayType;
import project.swithme.order.core.presentation.command.request.OrderCreateRequest;

@Component
public class OrderCreateValidator {

    private static final String STUDY_CAFE_IF = "studyCafeId";
    private static final String STUDY_CAFE_TICKET_PRICE = "studyCafeId";
    private static final String PRODUCT_PRICE = "productPrice";
    private static final String LOCKER_ID = "lockerId";
    private static final String LOCKER_PRICE = "lockerPrice";
    private static final String TITLE = "title";
    private static final String PAY_TYPE = "payType";
    private static final List<String> payTypes = Arrays.stream(PayType.values())
        .map(Objects::toString)
        .toList();

    public void validate(OrderCreateRequest orderCreateRequest) {
        validateStudyCafeId(orderCreateRequest);
        validateProductId(orderCreateRequest);
        validateProductPrice(orderCreateRequest);
        validateLockerId(orderCreateRequest);
        validateLockerPrice(orderCreateRequest);
        validateTitle(orderCreateRequest);
        validatePayType(orderCreateRequest);
    }

    private void validateStudyCafeId(OrderCreateRequest orderCreateRequest) {
        if (orderCreateRequest.getStudyCafeId() == null) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (orderCreateRequest.getStudyCafeId() <= 0) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private void validateProductId(OrderCreateRequest orderCreateRequest) {
        if (orderCreateRequest.getStudyCafeTicketId() == null) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (orderCreateRequest.getStudyCafeTicketId() <= 0) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private void validateProductPrice(OrderCreateRequest orderCreateRequest) {
        BigDecimal studyCafeTicketPrice = orderCreateRequest.getLockerPrice();
        if (studyCafeTicketPrice == null || studyCafeTicketPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private void validateLockerPrice(OrderCreateRequest orderCreateRequest) {
        BigDecimal lockerPrice = orderCreateRequest.getLockerPrice();
        if (lockerPrice == null || lockerPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private void validateLockerId(OrderCreateRequest orderCreateRequest) {
        if (orderCreateRequest.getLockerId() == null) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (orderCreateRequest.getLockerId() <= 0) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (orderCreateRequest.getLockerId() != null
            && orderCreateRequest.getLockerPrice() == null) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (orderCreateRequest.getLockerId() == null
            && orderCreateRequest.getLockerPrice() != null) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private void validateTitle(OrderCreateRequest orderCreateRequest) {
        if (orderCreateRequest.getTitle() == null) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (orderCreateRequest.getTitle().isBlank()) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private void validatePayType(OrderCreateRequest orderCreateRequest) {
        String payType = orderCreateRequest.getPayType();
        if (payType == null || payType.isBlank()) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
        if (!payTypes.contains(payType)) {
            throw new InvalidParameterException(createErrorFields(orderCreateRequest));
        }
    }

    private List<Field> createErrorFields(OrderCreateRequest orderCreateRequest) {
        return List.of(
            Field.of(STUDY_CAFE_IF, orderCreateRequest.getStudyCafeId()),
            Field.of(STUDY_CAFE_TICKET_PRICE, orderCreateRequest.getStudyCafeTicketId()),
            Field.of(PRODUCT_PRICE, orderCreateRequest.getStudyCafeTicketPrice()),
            Field.of(LOCKER_ID, orderCreateRequest.getLockerId()),
            Field.of(LOCKER_PRICE, orderCreateRequest.getStudyCafeTicketPrice()),
            Field.of(TITLE, orderCreateRequest.getTitle()),
            Field.of(PAY_TYPE, orderCreateRequest.getPayType())
        );
    }
}
