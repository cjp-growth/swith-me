package project.swithme.order.core.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private Instant createAt;

    @LastModifiedDate
    private Instant lastModifiedAt;

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }
}
