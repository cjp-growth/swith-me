package project.swithme.domain.core.payment.entity;

import static project.swithme.domain.core.payment.entity.AccountType.findByType;
import static project.swithme.domain.core.payment.entity.Bank.findByCode;
import static project.swithme.domain.core.payment.entity.RefundStatus.findByStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.Instant;
import lombok.Getter;

@Getter
@Embeddable
public class VirtualAccountInfo {

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_number")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "virtual_account_bank")
    private Bank virtualAccountBank;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "due_date")
    private Instant dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "refund_status")
    private RefundStatus refundStatus;

    @Column(name = "expired_date")
    private Boolean expiredDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "v_account_settlement_status")
    private SettlementStatus vAccountSettlementStatus;

    @Column(name = "holder_name")
    private String holderName;

    @Column(name = "refund_bank")
    @Enumerated(EnumType.STRING)
    private Bank refundBank;

    @Column(name = "refund_account_number")
    private String refundAccountNumber;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected VirtualAccountInfo() {
    }

    public VirtualAccountInfo(
        String accountType,
        String accountNumber,
        String bankCode,
        String customerName,
        Instant dueDate,
        String refundStatus,
        Boolean expiredDate,
        String vAccountSettlementStatus,
        String holderName,
        String refundBankCode,
        String refundAccountNumber
    ) {
        this.accountType = findByType(accountType);
        this.accountNumber = accountNumber;
        this.virtualAccountBank = findByCode(bankCode);
        this.customerName = customerName;
        this.dueDate = dueDate;
        this.refundStatus = findByStatus(refundStatus);
        this.expiredDate = expiredDate;
        this.vAccountSettlementStatus = SettlementStatus.findByStatus(
            vAccountSettlementStatus);
        this.holderName = holderName;
        this.refundBank = Bank.findByCode(refundBankCode);
        this.refundAccountNumber = refundAccountNumber;
    }

    public static VirtualAccountInfo createEmptyVirtualAccountInfo() {
        return new VirtualAccountInfo();
    }
}
