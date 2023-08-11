package project.swithme.payment.common.mockresponse;

import static order.PaymentFixture.createPayment;
import static order.PaymentFixture.getOrderUniqueId;
import static order.PaymentFixture.getPaymentKey;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import java.math.BigDecimal;
import java.util.UUID;
import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.facade.command.PaymentCommand;

public final class OrderCommandMockResponse {

    private OrderCommandMockResponse() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static OrderValidationCommand getCommand() {
        return new OrderValidationCommand(
            1L,
            1L,
            UUID.fromString(getOrderUniqueId()),
            PAYMENT_REQUEST,
            new BigDecimal(130_000)
        );
    }

    public static PaymentCommand createApprovedCommand() {
        Payment payment = createPayment(1L);
        return new PaymentCommand(
            payment.getVersion(),
            getPaymentKey(),
            payment.getPaymentType().name(),
            payment.getOrderId().toString(),
            payment.getOrderName(),
            payment.getMId(),
            payment.getCurrency(),
            "카드",
            payment.getTotalAmount(),
            payment.getBalanceAmount(),
            payment.getPaymentStatus().name(),
            payment.getRequestedAt(),
            payment.getApprovedAt(),
            payment.getUseEscrow(),
            payment.getLastTransactionKey(),
            payment.getSuppliedAmount(),
            payment.getVat(),
            payment.getCultureExpense(),
            payment.getTaxFreeAmount(),
            payment.getTaxExemptionAmount(),
            null,
            payment.getCardInfo(),
            null,
            payment.getSecret(),
            null,
            null,
            null,
            null,
            null,
            "KR",
            null
        );
    }

    public static PaymentCommand createNotApprovedCommand() {
        Payment payment = createPayment(1L);
        return new PaymentCommand(
            payment.getVersion(),
            getPaymentKey(),
            payment.getPaymentType().name(),
            payment.getOrderId().toString(),
            payment.getOrderName(),
            payment.getMId(),
            payment.getCurrency(),
            "카드",
            payment.getTotalAmount(),
            payment.getBalanceAmount(),
            payment.getPaymentStatus().name(),
            payment.getRequestedAt(),
            null,
            payment.getUseEscrow(),
            payment.getLastTransactionKey(),
            payment.getSuppliedAmount(),
            payment.getVat(),
            payment.getCultureExpense(),
            payment.getTaxFreeAmount(),
            payment.getTaxExemptionAmount(),
            null,
            payment.getCardInfo(),
            null,
            payment.getSecret(),
            null,
            null,
            null,
            null,
            null,
            "KR",
            null
        );
    }
}
