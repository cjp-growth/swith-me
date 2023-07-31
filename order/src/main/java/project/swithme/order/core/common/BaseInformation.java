package project.swithme.order.core.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;

@Getter
@Embeddable
public class BaseInformation {

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "deleted")
    private boolean deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 common 외부 패키지에서 호출하지 말 것.
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BaseInformation that)) {
            return false;
        }
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


