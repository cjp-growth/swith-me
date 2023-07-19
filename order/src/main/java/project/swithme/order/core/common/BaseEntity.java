package project.swithme.order.core.common;

import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@MappedSuperclass
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
