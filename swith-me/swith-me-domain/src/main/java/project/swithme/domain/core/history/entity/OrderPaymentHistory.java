package project.swithme.domain.core.history.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Getter
@RevisionEntity
@Entity(name = "order_payment_history")
public class OrderPaymentHistory implements Serializable {

    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @RevisionTimestamp
    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 history 외부 패키지에서 호출하지 말 것.
     */
    protected OrderPaymentHistory() {
    }

    public OrderPaymentHistory(
        Long id,
        Long createdAt
    ) {
        this.id = id;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof OrderPaymentHistory that)) {
            return false;
        }
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("revId: %s", id);
    }
}
