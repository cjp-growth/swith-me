package project.swithme.domain.core.payment.entity;

public enum RefundStatus {
    NONE,
    PENDING,
    FAILED,
    PARTIAL_FAILED,
    COMPLETED;

    public static RefundStatus findByStatus(String refundStatus) {
        if (refundStatus == null) {
            return null;
        }
        try {
            return RefundStatus.valueOf(refundStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바른 환불처리 상태를 입력해주세요.");
        }
    }
}
