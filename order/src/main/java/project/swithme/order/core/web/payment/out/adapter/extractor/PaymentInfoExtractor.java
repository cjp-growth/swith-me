package project.swithme.order.core.web.payment.out.adapter.extractor;

import static project.swithme.order.core.domain.payment.entity.EasyPayInfo.createEmptyInfo;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import project.swithme.order.core.domain.payment.entity.CardInfo;
import project.swithme.order.core.domain.payment.entity.CashReceiptInfo;
import project.swithme.order.core.domain.payment.entity.CashReceiptsInfo;
import project.swithme.order.core.domain.payment.entity.DiscountInfo;
import project.swithme.order.core.domain.payment.entity.EasyPayInfo;
import project.swithme.order.core.domain.payment.entity.GiftCertificateInfo;
import project.swithme.order.core.domain.payment.entity.MobilePhoneInfo;
import project.swithme.order.core.domain.payment.entity.TransferInfo;
import project.swithme.order.core.domain.payment.entity.VirtualAccountInfo;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;
import project.swithme.order.core.web.payment.out.adapter.response.CashReceiptResponse;
import project.swithme.order.core.web.payment.out.adapter.response.CashReceiptsResponse;
import project.swithme.order.core.web.payment.out.adapter.response.DiscountResponse;
import project.swithme.order.core.web.payment.out.adapter.response.EasyPayResponse;
import project.swithme.order.core.web.payment.out.adapter.response.GiftCertificateResponse;
import project.swithme.order.core.web.payment.out.adapter.response.MobilePhoneResponse;
import project.swithme.order.core.web.payment.out.adapter.response.TossPaymentApproveResponse;
import project.swithme.order.core.web.payment.out.adapter.response.TossPaymentCardResponse;
import project.swithme.order.core.web.payment.out.adapter.response.TossVirtualAccountResponse;
import project.swithme.order.core.web.payment.out.adapter.response.TransferResponse;

@Component
public class PaymentInfoExtractor {

    private static final DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

    private static final String TRUE = "true";

    public TossPaymentCommand extractInfo(TossPaymentApproveResponse response) {
        CardInfo cardInfo = extractCardInfo(response.getCard());
        VirtualAccountInfo virtualAccountInfo = extractVAccountInfo(response.getVirtualAccount());
        MobilePhoneInfo mobileInfo = extractMobilInfo(response.getMobilePhone());
        GiftCertificateInfo giftInfo = extractGiftInfo(response.getGiftCertificate());
        TransferInfo transferInfo = extractTransferInfo(response.getTransfer());
        EasyPayInfo easyPayInfo = extractEasyPayInfo(response.getEasyPay());
        // TODO
        CashReceiptInfo cashReceiptInfo = extractCashReceiptInfo(response.getCashReceipt());

        // TODO
        CashReceiptsInfo cashReceiptsInfo = extractCashReceiptsInfo(response.getCashReceipts());
        DiscountInfo discountInfo = extractDiscountInfo(response.getDiscount());
        return TossPaymentCommand.createPaymentInfo()
            .version(response.getVersion())
            .paymentKey(response.getPaymentKey())
            .type(response.getType())
            .orderId(response.getOrderId())
            .orderName(response.getOrderName())
            .mId(response.getMId())
            .currency(response.getCurrency())
            .method(response.getMethod())
            .totalAmount(response.getTotalAmount())
            .balanceAmount(response.getBalanceAmount())
            .status(response.getStatus())
            .requestedAt(getInstant(response.getRequestedAt()))
            .approvedAt(getInstant(response.getApprovedAt()))
            .useEscrow(convertTo(response.getUseEscrow()))
            .lastTransactionKey(response.getLastTransactionKey())
            .lastTransactionKey(response.getLastTransactionKey())
            .suppliedAmount(response.getSuppliedAmount())
            .vat(getDecimal(response.getVat()))
            .cultureExpense(response.getCultureExpense())
            .taxFreeAmount(response.getTaxFreeAmount())
            .taxExemptionAmount(response.getTaxExemptionAmount())
            .isPartialCancelable(response.getIsPartialCancelable())
            .cardInfo(cardInfo)
            .virtualAccountInfo(virtualAccountInfo)
            .secret(response.getSecret())
            .mobilePhoneInfo(mobileInfo)
            .giftInfo(giftInfo)
            .transferInfo(transferInfo)
            .easyPayInfo(easyPayInfo)
            .country(response.getCountry())
            .discountInfo(discountInfo)
            .build();
    }

    private CardInfo extractCardInfo(TossPaymentCardResponse cardResponse) {
        if (cardResponse == null) {
            return null;
        }
        return new CardInfo(
            cardResponse.getAmount(),
            cardResponse.getIssuerCode(),
            cardResponse.getAcquirerCode(),
            cardResponse.getNumber(),
            cardResponse.getInstallmentPlanMonths(),
            cardResponse.getApproveNo(),
            cardResponse.getUseCardPoint(),
            cardResponse.getCardType(),
            cardResponse.getOwnerType(),
            cardResponse.getAcquireStatus(),
            cardResponse.getIsInterestFree(),
            cardResponse.getInterestPayer()
        );
    }

    private VirtualAccountInfo extractVAccountInfo(TossVirtualAccountResponse accountResponse) {
        if (accountResponse == null) {
            return VirtualAccountInfo.createEmptyVirtualAccountInfo();
        }
        return new VirtualAccountInfo(
            accountResponse.getAccountType(),
            accountResponse.getAccountNumber(),
            accountResponse.getBankCode(),
            accountResponse.getCustomerName(),
            getInstant(accountResponse.getDueDate()),
            accountResponse.getRefundStatus(),
            accountResponse.getExpired(),
            accountResponse.getSettlementStatus(),
            accountResponse.getHolderName(),
            accountResponse.getBankCode(),
            accountResponse.getAccountNumber()
        );
    }

    private MobilePhoneInfo extractMobilInfo(MobilePhoneResponse mobilePhoneResponse) {
        if (mobilePhoneResponse == null) {
            return MobilePhoneInfo.createEmptyInfo();
        }
        return new MobilePhoneInfo(
            mobilePhoneResponse.getCustomerMobilePhone(),
            mobilePhoneResponse.getSettlementStatus(),
            mobilePhoneResponse.getReceiptUrl()
        );
    }

    private GiftCertificateInfo extractGiftInfo(GiftCertificateResponse giftCertificateResponse) {
        if (giftCertificateResponse == null) {
            return GiftCertificateInfo.createEmptyInfo();
        }
        return new GiftCertificateInfo(
            giftCertificateResponse.getApproveNo(),
            giftCertificateResponse.getSettlementStatus()
        );
    }

    private TransferInfo extractTransferInfo(TransferResponse transferResponse) {
        if (transferResponse == null) {
            return TransferInfo.createEmptyInfo();
        }
        return new TransferInfo(
            transferResponse.getBankCode(),
            transferResponse.getSettlementStatus()
        );
    }

    private EasyPayInfo extractEasyPayInfo(EasyPayResponse easyPayResponse) {
        if (easyPayResponse == null) {
            return createEmptyInfo();
        }
        return new EasyPayInfo(
            easyPayResponse.getProvider(),
            easyPayResponse.getAmount(),
            easyPayResponse.getDiscountAmount()
        );
    }

    // TODO. 추 후 구현
    private CashReceiptInfo extractCashReceiptInfo(CashReceiptResponse cashReceiptResponse) {
        if (cashReceiptResponse == null) {
            return CashReceiptInfo.createEmptyInfo();
        }
        return new CashReceiptInfo(
            cashReceiptResponse.getType(),
            cashReceiptResponse.getReceiptKey(),
            cashReceiptResponse.getIssueNumber(),
            cashReceiptResponse.getReceiptUrl(),
            cashReceiptResponse.getAmount(),
            cashReceiptResponse.getTaxFreeAmount()
        );
    }

    // TODO. 추 후 구현
    private CashReceiptsInfo extractCashReceiptsInfo(CashReceiptsResponse cashReceipts) {
        return CashReceiptsInfo.createEmptyInfo();
    }

    private DiscountInfo extractDiscountInfo(DiscountResponse discountResponse) {
        if (discountResponse == null) {
            return DiscountInfo.createEmptyInfo();
        }
        return new DiscountInfo(discountResponse.getAmount());
    }

    private Instant getInstant(String date) {
        if (date == null) {
            return null;
        }
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, formatter);
        return offsetDateTime.toInstant();
    }

    private BigDecimal getDecimal(String value) {
        if (value == null) {
            return null;
        }
        return new BigDecimal(value);
    }

    private Boolean convertTo(String bool) {
        if (bool == null) {
            return null;
        }
        return TRUE.equals(bool) ? Boolean.TRUE : Boolean.FALSE;
    }
}
