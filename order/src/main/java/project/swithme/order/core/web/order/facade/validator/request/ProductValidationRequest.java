package project.swithme.order.core.web.order.facade.validator.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductValidationRequest {

    private Long studyCafeId;
    private Long productId;
    private BigDecimal productPrice;
    private Long lockerId;
    private BigDecimal lockerPrice;

    private ProductValidationRequest() {
    }

    public ProductValidationRequest(
            Long studyCafeId,
            Long productId,
            BigDecimal productPrice,
            Long lockerId,
            BigDecimal lockerPrice
    ) {
        this.studyCafeId = studyCafeId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.lockerId = lockerId;
        this.lockerPrice = lockerPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "Response: [StudyCafeId: %s, ProductId: %s, ProductPrice: %s, LockerId: %s, LockerPrice: %s]",
                studyCafeId, productId, productPrice, lockerId, lockerPrice
        );
    }
}
