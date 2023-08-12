package project.swithme.domain.common.sut.payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import project.swithme.domain.core.payment.entity.Payment;

public final class PaymentSut {

    private final Payment payment;

    public PaymentSut(Payment payment) {
        this.payment = payment;
    }

    private PaymentSut() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public PaymentSut shouldExist() {
        assertNotNull(payment);
        return this;
    }

    public PaymentSut withoutId() {
        assertNull(payment.getId());
        return this;
    }

    public PaymentSut withVersion() {
        assertNotNull(payment.getVersion());
        return this;
    }

    public PaymentSut withPaymentKey() {
        assertNotNull(payment.getPaymentKey());
        return this;
    }

    public PaymentSut withPaymentType() {
        assertNotNull(payment.getPaymentType());
        return this;
    }

    public PaymentSut withOrderId() {
        assertNotNull(payment.getOrderId());
        return this;
    }

    public PaymentSut withOrderName() {
        assertNotNull(payment.getOrderName());
        return this;
    }

    public PaymentSut withoutMid() {
        assertNull(payment.getMId());
        return this;
    }

    public PaymentSut withCurrency() {
        assertNotNull(payment.getCurrency());
        return this;
    }

    public PaymentSut withMethod() {
        assertNotNull(payment.getMethod());
        return this;
    }

    public PaymentSut withTotalAmount() {
        assertNotNull(payment.getTotalAmount());
        return this;
    }

    public PaymentSut withBalancedAmount() {
        assertNotNull(payment.getBalanceAmount());
        return this;
    }

    public PaymentSut withPaymentStatus() {
        assertNotNull(payment.getPaymentStatus());
        return this;
    }

    public PaymentSut withRequestedAt() {
        assertNotNull(payment.getRequestedAt());
        return this;
    }

    public PaymentSut withApprovedAt() {
        assertNotNull(payment.getApprovedAt());
        return this;
    }

    public PaymentSut withUseEscrow() {
        assertNotNull(payment.getUseEscrow());
        return this;
    }

    public PaymentSut withLastTransactionKey() {
        assertNotNull(payment.getLastTransactionKey());
        return this;
    }

    public PaymentSut withSuppliedAmount() {
        assertNotNull(payment.getSuppliedAmount());
        return this;
    }

    public PaymentSut withVat() {
        assertNotNull(payment.getVat());
        return this;
    }

    public PaymentSut withCultureExpense() {
        assertNotNull(payment.getCultureExpense());
        return this;
    }

    public PaymentSut withTaxFreeAmount() {
        assertNotNull(payment.getTaxFreeAmount());
        return this;
    }

    public PaymentSut withTaxExemptionAmount() {
        assertNotNull(payment.getTaxExemptionAmount());
        return this;
    }

    public PaymentSut withPartialCancelable() {
        assertNotNull(payment.getIsPartialCancelable());
        return this;
    }

    public PaymentSut withCardInfo() {
        assertNotNull(payment.getCardInfo());
        return this;
    }

    public PaymentSut withoutVirtualAccountInfo() {
        assertNull(payment.getVirtualAccountInfo());
        return this;
    }

    public PaymentSut withoutSecretKey() {
        assertNull(payment.getSecret());
        return this;
    }

    public PaymentSut withoutMobileInfo() {
        assertNull(payment.getMobileInfo());
        return this;
    }

    public PaymentSut withoutGiftCertificateInfo() {
        assertNull(payment.getGiftCertificateInfo());
        return this;
    }

    public PaymentSut withoutTransferInfo() {
        assertNull(payment.getTransferInfo());
        return this;
    }

    public PaymentSut withoutReceiptUrl() {
        assertNull(payment.getReceiptUrl());
        return this;
    }

    public PaymentSut withoutEasyPayInfo() {
        assertNull(payment.getEasyPayInfo());
        return this;
    }

    public PaymentSut withCountry() {
        assertNotNull(payment.getCountry());
        return this;
    }

    public PaymentSut withoutDiscountedAmount() {
        assertNull(payment.getDiscountedAmount());
        return this;
    }

    public PaymentSut withBaseInformation() {
        assertNotNull(payment.getBaseInformation());
        return this;
    }
}
