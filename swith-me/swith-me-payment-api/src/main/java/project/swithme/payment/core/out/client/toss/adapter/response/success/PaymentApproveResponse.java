package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class PaymentApproveResponse {

    private static final String EMPTY_MESSAGE = "";

    private String version;
    private String paymentKey;
    private String type;
    private String orderId;
    private String orderName;
    private String mId;
    private String currency;
    private String method;
    private BigDecimal totalAmount;
    private BigDecimal balanceAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private String useEscrow;
    private String lastTransactionKey;
    private BigDecimal suppliedAmount;
    private String vat;
    private Boolean cultureExpense;
    private BigDecimal taxFreeAmount;
    private BigDecimal taxExemptionAmount;
    private CancelsResponse cancels;
    private Boolean isPartialCancelable;
    private PaymentCardResponse card;
    private PaymentVirtualAccountResponse virtualAccount;
    private String secret;
    private MobilePhoneResponse mobilePhone;
    private GiftCertificateResponse giftCertificate;
    private TransferResponse transfer;
    private ReceiptResponse receipt;
    private CheckoutResponse checkout;
    private EasyPayResponse easyPay;
    private String country;
    private FailureResponse failure;
    private CashReceiptResponse cashReceipt;
    private CashReceiptsResponse cashReceipts;
    private DiscountResponse discount;

    private PaymentApproveResponse() {
    }

    public PaymentApproveResponse(
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
        String requestedAt,
        String approvedAt,
        String useEscrow,
        String lastTransactionKey,
        BigDecimal suppliedAmount,
        String vat,
        Boolean cultureExpense,
        BigDecimal taxFreeAmount,
        BigDecimal taxExemptionAmount,
        CancelsResponse cancels,
        Boolean isPartialCancelable,
        PaymentCardResponse card,
        PaymentVirtualAccountResponse virtualAccount,
        String secret,
        MobilePhoneResponse mobilePhone,
        GiftCertificateResponse giftCertificate,
        TransferResponse transfer,
        ReceiptResponse receipt,
        CheckoutResponse checkout,
        EasyPayResponse easyPay,
        String country,
        FailureResponse failure,
        CashReceiptResponse cashReceipt,
        CashReceiptsResponse cashReceipts,
        DiscountResponse discount
    ) {
        this.version = version;
        this.paymentKey = paymentKey;
        this.type = type;
        this.orderId = orderId;
        this.orderName = orderName;
        this.mId = mId;
        this.currency = currency;
        this.method = method;
        this.totalAmount = totalAmount;
        this.balanceAmount = balanceAmount;
        this.status = status;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
        this.useEscrow = useEscrow;
        this.lastTransactionKey = lastTransactionKey;
        this.suppliedAmount = suppliedAmount;
        this.vat = vat;
        this.cultureExpense = cultureExpense;
        this.taxFreeAmount = taxFreeAmount;
        this.taxExemptionAmount = taxExemptionAmount;
        this.cancels = cancels;
        this.isPartialCancelable = isPartialCancelable;
        this.card = card;
        this.virtualAccount = virtualAccount;
        this.secret = secret;
        this.mobilePhone = mobilePhone;
        this.giftCertificate = giftCertificate;
        this.transfer = transfer;
        this.receipt = receipt;
        this.checkout = checkout;
        this.easyPay = easyPay;
        this.country = country;
        this.failure = failure;
        this.cashReceipt = cashReceipt;
        this.cashReceipts = cashReceipts;
        this.discount = discount;
    }

    //    @Override
//    public String toString() {
//        return String.format("orderId: %s", orderId);
//    }
}
