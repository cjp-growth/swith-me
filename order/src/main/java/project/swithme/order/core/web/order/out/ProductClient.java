package project.swithme.order.core.web.order.out;

import java.math.BigDecimal;

public interface ProductClient {

    void validateProductDetails(Long studyCafeId, Long productId, BigDecimal productPrice, Long lockerId, BigDecimal lockerPrice);
}
