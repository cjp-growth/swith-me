package project.swithme.order.core.presentation.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import project.swithme.domain.core.order.entity.PayType;
import project.swithme.order.core.facade.command.OrderCreateCommand;

@Getter
public class OrderCreateRequest {

    @NotNull(message = "스터디 카페 PK를 입력해주세요.")
    @Positive(message = "올바른 스터디 카페 PK를 입력해주세요.")
    private Long studyCafeId;

    @NotNull(message = "상품 PK를 입력해주세요.")
    @Positive(message = "올바른 상품 PK를 입력해주세요.")
    private Long studyCafeTicketId;

    @NotNull(message = "상품 금액을 입력해주세요.")
    @DecimalMin(value = "0", message = "올바른 스터디 카페 이용권 금액을 입력해주세요.")
    private BigDecimal studyCafeTicketPrice;

    @Nullable
    @Positive(message = "올바른 락커 PK를 입력해주세요.")
    private Long lockerId;

    @Nullable
    @DecimalMin(value = "0", message = "올바른 락커 이용권 금액을 입력해주세요.")
    private BigDecimal lockerPrice;

    @Nullable
    @NotBlank(message = "주문명을 입력해주세요.")
    private String title;

    @NotNull(message = "결제 수단을 입력해주세요.")
    @NotBlank(message = "올바른 결제 수단을 입력해주세요.")
    private String payType;

    private OrderCreateRequest() {
    }

    public OrderCreateRequest(
        Long studyCafeId,
        Long studyCafeTicketId,
        BigDecimal studyCafeTicketPrice,
        Long lockerId,
        BigDecimal lockerPrice,
        String title,
        String payType
    ) {
        this.studyCafeId = studyCafeId;
        this.studyCafeTicketId = studyCafeTicketId;
        this.studyCafeTicketPrice = studyCafeTicketPrice;
        this.lockerId = lockerId;
        this.lockerPrice = lockerPrice;
        this.title = title;
        this.payType = payType;
    }

    public OrderCreateCommand toCommand() {
        return new OrderCreateCommand(
            studyCafeId,
            studyCafeTicketId,
            studyCafeTicketPrice,
            lockerId,
            lockerPrice,
            title,
            PayType.findPayType(payType)
        );
    }

    @Override
    public String toString() {
        return String.format(
            "studyCafeId: %s, studyCafeTicketId: %s, studyCafeTicketPrice: %s, lockerId: %s, lockerPrice: %s, title: %s, payType: %s",
            studyCafeId, studyCafeTicketId, studyCafeTicketPrice, lockerId, lockerPrice, title,
            payType
        );
    }
}
