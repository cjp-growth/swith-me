package project.swithme.order.core.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class BaseInformation {

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "deleted")
    private boolean deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 createOrder 외부 패키지에서 호출하지 말 것.
     */
    protected BaseInformation() {
    }

    public BaseInformation(Long createdBy) {
        this.createdBy = createdBy;
        this.lastModifiedBy = null;
        this.deleted = false;
    }

    public BaseInformation(
            Long createdBy,
            Long lastModifiedBy,
            boolean deleted
    ) {
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.deleted = deleted;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseInformation that)) return false;
        return getCreatedBy().equals(that.getCreatedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedBy());
    }

    @Override
    public String toString() {
        return createdBy.toString();
    }
}


