package project.swithme.payment.core.out.client.toss.adapter.response.success;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public String toString() {
        return String.format("orderId: %s", orderId);
    }
}
