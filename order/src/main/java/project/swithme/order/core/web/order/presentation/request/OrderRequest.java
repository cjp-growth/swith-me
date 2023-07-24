package project.swithme.order.core.web.order.presentation.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import project.swithme.order.core.domain.order.entity.PayType;
import project.swithme.order.core.web.order.application.command.OrderCreateCommand;

import java.math.BigDecimal;

@Getter
public class OrderRequest {

    @NotNull(message = "스터디 카페 PK를 입력해주세요.")
    @Positive(message = "올바른 스터디 카페 PK를 입력해주세요.")
    private Long studyCafeId;

    @NotNull(message = "상품 PK를 입력해주세요.")
    @Positive(message = "올바른 상품 PK를 입력해주세요.")
    private Long productId;

    @NotNull(message = "상품 금액을 입력해주세요.")
    @DecimalMin(value = "0", message = "올바른 금액을 입력해주세요.")
    private BigDecimal productPrice;

    @Nullable
    private Long lockerId;

    @Nullable
    private BigDecimal lockerPrice;

    @NotNull(message = "결제 수단을 입력해주세요.")
    @NotBlank(message = "올바른 결제 수단을 입력해주세요.")
    private String payType;

    private OrderRequest() {
    }

    public OrderRequest(
            Long studyCafeId,
            Long productId,
            BigDecimal productPrice,
            Long lockerId,
            BigDecimal lockerPrice,
            String payType
    ) {
        this.studyCafeId = studyCafeId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.lockerId = lockerId;
        this.lockerPrice = lockerPrice;
        this.payType = payType;
    }

    public OrderCreateCommand toCommand() {
        return new OrderCreateCommand(
                studyCafeId,
                productId,
                productPrice,
                lockerId,
                lockerPrice,
                PayType.findPayType(payType)
        );
    }
}
