package project.swithme.payment.core.facade.command;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import project.swithme.domain.core.payment.entity.CardInfo;
import project.swithme.domain.core.payment.entity.DiscountInfo;
import project.swithme.domain.core.payment.entity.EasyPayInfo;
import project.swithme.domain.core.payment.entity.GiftCertificateInfo;
import project.swithme.domain.core.payment.entity.MobilePhoneInfo;
import project.swithme.domain.core.payment.entity.PaymentMethod;
import project.swithme.domain.core.payment.entity.PaymentStatus;
import project.swithme.domain.core.payment.entity.TransferInfo;
import project.swithme.domain.core.payment.entity.VirtualAccountInfo;

@Getter
public class PaymentCommand {

    private String version;
    private String paymentKey;
    private String type;
    private String orderId;
    private String orderName;
    private String mId;
    private String currency;
    private PaymentMethod method;
    private BigDecimal totalAmount;
    private BigDecimal balanceAmount;
    private PaymentStatus status;
    private Instant requestedAt;
    private Instant approvedAt;
    private Boolean useEscrow;
    private String lastTransactionKey;
    private BigDecimal suppliedAmount;
    private BigDecimal vat;
    private Boolean cultureExpense;
    private BigDecimal taxFreeAmount;
    private BigDecimal taxExemptionAmount;
    private VirtualAccountInfo virtualAccountInfo;
    private Boolean isPartialCancelable;
    private CardInfo cardInfo;
    private String secret;
    private MobilePhoneInfo mobilePhoneInfo;
    private GiftCertificateInfo giftCertificate;
    private TransferInfo transferInfo;
    private String receipt;
    private EasyPayInfo easyPayInfo;
    private String country;
    private String failureMessage;
    private DiscountInfo discountInfo;

    protected PaymentCommand() {
    }

    @Builder(builderMethodName = "createPaymentInfo")
    public PaymentCommand(
        String version,
        String paymentKey,
        String type,
        String orderId,
        String orderName,
        String mId,
        String currency,
        String method,
        BigDecimal totalAmount,
        BigDecimal balanceAmount,
        String status,
        Instant requestedAt,
        Instant approvedAt,
        Boolean useEscrow,
        String lastTransactionKey,
        BigDecimal suppliedAmount,
        BigDecimal vat,
        Boolean cultureExpense,
        BigDecimal taxFreeAmount,
        BigDecimal taxExemptionAmount,
        VirtualAccountInfo virtualAccountInfo,
        CardInfo cardInfo,
        Boolean isPartialCancelable,
        String secret,
        MobilePhoneInfo mobilePhoneInfo,
        GiftCertificateInfo giftInfo,
        TransferInfo transferInfo,
        String failureMessage,
        EasyPayInfo easyPayInfo,
        String country,
        DiscountInfo discountInfo
    ) {
        this.version = version;
        this.paymentKey = paymentKey;
        this.type = type;
        this.orderId = orderId;
        this.orderName = orderName;
        this.mId = mId;
        this.currency = currency;
        this.method = PaymentMethod.findMethod(method);
        this.totalAmount = totalAmount;
        this.balanceAmount = balanceAmount;
        this.status = PaymentStatus.valueOf(status);
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
        this.useEscrow = useEscrow;
        this.lastTransactionKey = lastTransactionKey;
        this.suppliedAmount = suppliedAmount;
        this.vat = vat;
        this.cultureExpense = cultureExpense;
        this.taxFreeAmount = taxFreeAmount;
        this.taxExemptionAmount = taxExemptionAmount;
        this.virtualAccountInfo = virtualAccountInfo;
        this.cardInfo = cardInfo;
        this.isPartialCancelable = isPartialCancelable;
        this.secret = secret;
        this.mobilePhoneInfo = mobilePhoneInfo;
        this.giftCertificate = giftInfo;
        this.transferInfo = transferInfo;
        this.failureMessage = failureMessage;
        this.easyPayInfo = easyPayInfo;
        this.country = country;
        this.discountInfo = discountInfo;
    }

    public boolean isApproved() {
        return this.approvedAt != null;
    }

    public BigDecimal getDiscount() {
        if (this.discountInfo == null) {
            return null;
        }
        return discountInfo.getDiscountedAmount();
    }
}
