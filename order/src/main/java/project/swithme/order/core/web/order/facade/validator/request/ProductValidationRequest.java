package project.swithme.order.core.web.order.facade.validator.request;

import java.math.BigDecimal;

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

    public Long getStudyCafeId() {
        return studyCafeId;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Long getLockerId() {
        return lockerId;
    }

    public BigDecimal getLockerPrice() {
        return lockerPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "Response: [StudyCafeId: %s, ProductId: %s, ProductPrice: %s, LockerId: %s, LockerPrice: %s]",
                studyCafeId, productId, productPrice, lockerId, lockerPrice
        );
    }
}
