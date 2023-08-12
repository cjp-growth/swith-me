package project.swithme.domain.common.sut.payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import project.swithme.domain.core.payment.entity.VirtualAccountInfo;

public final class VirtualAccountSut {

    private final VirtualAccountInfo virtualAccountInfo;

    private VirtualAccountSut() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public VirtualAccountSut(VirtualAccountInfo virtualAccountInfo) {
        this.virtualAccountInfo = virtualAccountInfo;
    }

    public VirtualAccountSut shouldExist() {
        assertNotNull(virtualAccountInfo);
        return this;
    }

    public VirtualAccountSut withAccountType() {
        assertNotNull(virtualAccountInfo.getAccountType());
        return this;
    }

    public VirtualAccountSut withAccountNumber() {
        assertNotNull(virtualAccountInfo.getAccountNumber());
        return this;
    }

    public VirtualAccountSut withVirtualAccountBank() {
        assertNotNull(virtualAccountInfo.getVirtualAccountBank());
        return this;
    }

    public VirtualAccountSut withCustomerName() {
        assertNotNull(virtualAccountInfo.getCustomerName());
        return this;
    }

    public VirtualAccountSut withDueDate() {
        assertNotNull(virtualAccountInfo.getDueDate());
        return this;
    }

    public VirtualAccountSut withRefundStatus() {
        assertNotNull(virtualAccountInfo.getRefundStatus());
        return this;
    }

    public VirtualAccountSut withExpiredDate() {
        assertNotNull(virtualAccountInfo.getExpiredDate());
        return this;
    }

    public VirtualAccountSut withVAccountSettlementStatus() {
        assertNotNull(virtualAccountInfo.getVAccountSettlementStatus());
        return this;
    }

    public VirtualAccountSut withHolderName() {
        assertNotNull(virtualAccountInfo.getHolderName());
        return this;
    }

    public VirtualAccountSut withRefundBank() {
        assertNotNull(virtualAccountInfo.getRefundBank());
        return this;
    }

    public VirtualAccountSut withRefundAccountNumber() {
        assertNotNull(virtualAccountInfo.getRefundAccountNumber());
        return this;
    }
}
