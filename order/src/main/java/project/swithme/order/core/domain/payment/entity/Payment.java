package project.swithme.order.core.domain.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import project.swithme.order.core.common.BaseEntity;
import project.swithme.order.core.common.BaseInformation;

@Getter
@Entity(name = "toss")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "payment_key")
    private String paymentKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "m_id")
    private String mId;

    @Column(name = "currency")
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private Method method;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "balance_amount")
    private BigDecimal balanceAmount;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "requested_at")
    private Instant requestedAt;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "use_escrow")
    private Boolean useEscrow;

    @Column(name = "last_transaction_key")
    private String lastTransactionKey;

    @Column(name = "supplied_amount")
    private BigDecimal suppliedAmount;

    @Column(name = "vat")
    private BigDecimal vat;

    @Column(name = "culture_expense")
    private Boolean cultureExpense;

    @Column(name = "tax_free_amount")
    private BigDecimal taxFreeAmount;

    @Column(name = "tax_exemption_amount")
    private BigDecimal taxExemptionAmount;

    @Column(name = "is_partial_cancelable")
    private Boolean isPartialCancelable;

    @Embedded
    private CardInfo cardInfo;

    @Embedded
    private VirtualAccountInfo virtualAccountInfo;

    @Column(name = "secret")
    private String secret;

    @Embedded
    private MobilePhoneInfo mobileInfo;

    @Embedded
    private GiftCertificateInfo giftCertificateInfo;

    @Embedded
    private TransferInfo transferInfo;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Embedded
    private EasyPayInfo easyPayInfo;

    @Column(name = "country")
    private String country;

    @Column(name = "discounted_amount")
    private BigDecimal discountedAmount;

    @Embedded
    private BaseInformation baseInformation;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected Payment() {
    }

    @Builder
    public Payment(
        Long id,
        Long orderId,
        String paymentKey,
        PaymentType paymentType,
        String mId,
        String version,
        String orderName,
        String currency,
        Method method,
        BigDecimal totalAmount,
        BigDecimal balanceAmount,
        BigDecimal suppliedAmount,
        BigDecimal vat,
        PaymentStatus paymentStatus,
        Instant requestedAt,
        Instant approvedAt,
        Boolean useEscrow,
        String lastTransactionKey,
        Boolean cultureExpense,
        BigDecimal taxFreeAmount,
        BigDecimal taxExemptionAmount,
        Boolean isPartialCancelable,
        CardInfo cardInfo,
        VirtualAccountInfo virtualAccountInfo,
        String secret,
        MobilePhoneInfo mobileInfo,
        GiftCertificateInfo giftCertificateInfo,
        TransferInfo transferInfo,
        String receiptUrl,
        EasyPayInfo easyPayInfo,
        String country,
        BigDecimal discountedAmount,
        BaseInformation baseInformation
    ) {
        this.id = id;
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.paymentType = paymentType;
        this.mId = mId;
        this.version = version;
        this.orderName = orderName;
        this.currency = currency;
        this.method = method;
        this.totalAmount = totalAmount;
        this.balanceAmount = balanceAmount;
        this.suppliedAmount = suppliedAmount;
        this.vat = vat;
        this.paymentStatus = paymentStatus;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
        this.useEscrow = useEscrow;
        this.lastTransactionKey = lastTransactionKey;
        this.cultureExpense = cultureExpense;
        this.taxFreeAmount = taxFreeAmount;
        this.taxExemptionAmount = taxExemptionAmount;
        this.isPartialCancelable = isPartialCancelable;
        this.cardInfo = cardInfo;
        this.virtualAccountInfo = virtualAccountInfo;
        this.secret = secret;
        this.mobileInfo = mobileInfo;
        this.giftCertificateInfo = giftCertificateInfo;
        this.transferInfo = transferInfo;
        this.receiptUrl = receiptUrl;
        this.easyPayInfo = easyPayInfo;
        this.country = country;
        this.discountedAmount = discountedAmount;
        this.baseInformation = baseInformation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Payment payment)) {
            return false;
        }
        return getId().equals(payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
