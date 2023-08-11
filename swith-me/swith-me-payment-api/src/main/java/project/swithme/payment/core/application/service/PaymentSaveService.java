package project.swithme.payment.core.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.domain.core.common.BaseInformation;
import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.domain.core.payment.repository.PaymentJpaRepository;
import project.swithme.payment.core.application.PaymentSaveUseCase;
import project.swithme.payment.core.facade.command.PaymentCommand;

@Service
@RequiredArgsConstructor
public class PaymentSaveService implements PaymentSaveUseCase {

    private final PaymentJpaRepository paymentRepository;

    @Override
    public Payment save(
        Long userId,
        Long orderId,
        PaymentType paymentType,
        PaymentCommand command
    ) {
        Payment payment = createPayment(userId, orderId, paymentType, command);
        return paymentRepository.save(payment);
    }

    private Payment createPayment(
        Long userId,
        Long orderId,
        PaymentType paymentType,
        PaymentCommand command
    ) {
        return Payment.builder()
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
