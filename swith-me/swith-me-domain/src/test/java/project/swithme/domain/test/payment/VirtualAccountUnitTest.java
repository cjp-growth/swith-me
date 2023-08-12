package project.swithme.domain.test.payment;

import static order.PaymentFixture.createVirtualAccountInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.swithme.domain.core.payment.entity.VirtualAccountInfo.createEmptyVirtualAccountInfo;
import java.time.Instant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.domain.common.sut.payment.VirtualAccountSut;
import project.swithme.domain.core.payment.entity.VirtualAccountInfo;

@DisplayName("[UnitTest] 가상 계좌 단위 테스트")
class VirtualAccountUnitTest {

    @Test
    @DisplayName("가상계좌 객체를 생성할 수 있다.")
    void virtual_account_create_test() {
        VirtualAccountSut sut = new VirtualAccountSut(createVirtualAccountInfo());

        sut.shouldExist()
            .withAccountType()
            .withAccountNumber()
            .withRefundBank()
            .withCustomerName()
            .withDueDate()
            .withRefundStatus()
            .withExpiredDate()
            .withVAccountSettlementStatus()
            .withHolderName()
            .withRefundAccountNumber();

        assertNotNull(createEmptyVirtualAccountInfo());
        assertNotNull(sut);
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        VirtualAccountInfo virtualAccountInfo = createVirtualAccountInfo();
        VirtualAccountInfo otherVirtualAccountInfo = new VirtualAccountInfo(
            "일반",
            "4123-****-1546",
            "039",
            "Kang",
            Instant.now().plusSeconds(20_000),
            "NONE",
            Boolean.FALSE,
            "INCOMPLETED",
            "Jung",
            "039",
            "1234-****-1546"
        );

        VirtualAccountInfo otherVirtualAccountInfoB = new VirtualAccountInfo(
            "일반",
            "1234-****-1546",
            "039",
            "Kang",
            Instant.now().plusSeconds(0_000),
            "NONE",
            Boolean.FALSE,
            "INCOMPLETED",
            "Jung",
            "039",
            "1234-****-1546"
        );

        VirtualAccountInfo otherVirtualAccountInfoC = new VirtualAccountInfo(
            "일반",
            "1234-****-1546",
            "039",
            "Jung",
            Instant.now().plusSeconds(20_000),
            "NONE",
            Boolean.FALSE,
            "INCOMPLETED",
            "Jung",
            "039",
            "1234-****-1546"
        );

        assertFalse("1".equals(virtualAccountInfo));
        assertTrue(virtualAccountInfo.equals(virtualAccountInfo));
        assertTrue(!virtualAccountInfo.equals(otherVirtualAccountInfo));
        assertTrue(!virtualAccountInfo.equals(otherVirtualAccountInfoB));
        assertTrue(!virtualAccountInfo.equals(otherVirtualAccountInfoC));
    }


    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        VirtualAccountInfo virtualAccountInfo = createVirtualAccountInfo();
        VirtualAccountInfo otherVirtualAccountInfo = new VirtualAccountInfo(
            "일반",
            "4123-****-1546",
            "039",
            "Kang",
            Instant.now().plusSeconds(10_000),
            "NONE",
            Boolean.FALSE,
            "INCOMPLETED",
            "Jung",
            "039",
            "1234-****-1546"
        );

        assertTrue(virtualAccountInfo.hashCode() == virtualAccountInfo.hashCode());
        assertFalse(virtualAccountInfo.hashCode() == otherVirtualAccountInfo.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        VirtualAccountInfo virtualAccountInfo = createVirtualAccountInfo();
        String expected = String.format("accountNumber: %s, bank: %s, customer: %s, dueDate: %s",
            virtualAccountInfo.getAccountNumber(),
            virtualAccountInfo.getRefundBank(),
            virtualAccountInfo.getCustomerName(),
            virtualAccountInfo.getDueDate()
        );

        assertEquals(expected, virtualAccountInfo.toString());
    }
}
