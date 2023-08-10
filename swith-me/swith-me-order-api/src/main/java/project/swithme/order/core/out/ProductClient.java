package project.swithme.order.core.out;

import java.math.BigDecimal;

public interface ProductClient {

    void validateProductDetails(
        Long studyCafeId,
        Long productId,
        BigDecimal productPrice,
        Long lockerId,
        BigDecimal lockerPrice
    );
}
