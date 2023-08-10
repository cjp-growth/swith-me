package project.swithme.domain.core.payment.entity;

import static project.swithme.domain.core.payment.entity.Bank.findByCode;
import static project.swithme.domain.core.payment.entity.SettlementStatus.findByStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Objects;
import lombok.Getter;

@Getter
@Embeddable
public class TransferInfo {

    @Column(name = "transfer_bank")
    @Enumerated(EnumType.STRING)
    private Bank transferBank;

    @Column(name = "transfer_settlement_status")
    @Enumerated(EnumType.STRING)
    private SettlementStatus transferSettlementStatus;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected TransferInfo() {
    }

    public TransferInfo(
        String transferBankCode,
        String transferSettlementStatus
    ) {
        this.transferBank = findByCode(transferBankCode);
        this.transferSettlementStatus = findByStatus(transferSettlementStatus);
    }

    public static TransferInfo createEmptyInfo() {
        return new TransferInfo();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TransferInfo that)) {
            return false;
        }
        return getTransferBank() == that.getTransferBank()
            && getTransferSettlementStatus() == that.getTransferSettlementStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransferBank(), getTransferSettlementStatus());
    }
}
