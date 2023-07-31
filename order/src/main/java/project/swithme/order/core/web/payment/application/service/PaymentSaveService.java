package project.swithme.order.core.web.payment.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.order.core.common.BaseInformation;
import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.domain.payment.entity.Toss;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;
import project.swithme.order.core.domain.payment.persistence.toss.TossJpaRepository;
import project.swithme.order.core.web.payment.application.PaymentSaveUseCase;

@Service
@RequiredArgsConstructor
public class PaymentSaveService implements PaymentSaveUseCase {

    private final TossJpaRepository tossRepository;

    @Override
    public Toss save(
        Long userId,
        Long orderId,
        PaymentType paymentType,
        TossPaymentCommand command
    ) {
        Toss toss = createTossPayment(userId, orderId, paymentType, command);
        return tossRepository.save(toss);
    }

    private Toss createTossPayment(
        Long userId,
        Long orderId,
        PaymentType paymentType,
        TossPaymentCommand command
    ) {
        return Toss.builder()
            .version(command.getVersion())
            .paymentKey(command.getPaymentKey())
            .paymentType(paymentType)
            .orderId(orderId)
            .orderName(command.getOrderName())
            .mId(command.getMId())
            .currency(command.getCurrency())
            .method(command.getMethod())
            .totalAmount(command.getTotalAmount())
            .balanceAmount(command.getBalanceAmount())
            .paymentStatus(command.getStatus())
            .requestedAt(command.getRequestedAt())
            .approvedAt(command.getApprovedAt())
            .useEscrow(command.getUseEscrow())
            .lastTransactionKey(command.getLastTransactionKey())
            .suppliedAmount(command.getSuppliedAmount())
            .vat(command.getVat())
            .cultureExpense(command.getCultureExpense())
            .taxFreeAmount(command.getTaxFreeAmount())
            .taxExemptionAmount(command.getTaxExemptionAmount())
            .isPartialCancelable(command.getIsPartialCancelable())
            .cardInfo(command.getCardInfo())
            .virtualAccountInfo(command.getVirtualAccountInfo())
            .secret(command.getSecret())
            .mobileInfo(command.getMobilePhoneInfo())
            .giftCertificateInfo(command.getGiftCertificate())
            .transferInfo(command.getTransferInfo())
            .receiptUrl(command.getReceipt())
            .easyPayInfo(command.getEasyPayInfo())
            .country(command.getCountry())
            .discountedAmount(command.getDiscount())
            .baseInformation(new BaseInformation(userId))
            .build();
    }
}
