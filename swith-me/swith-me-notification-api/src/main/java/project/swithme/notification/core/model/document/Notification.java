package project.swithme.notification.core.model.document;

import static project.swithme.notification.common.utils.UUIDGenerators.createUUID;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "notification")
public class Notification {

    @Id
    private String _id;

    @Field(name = "uniqueId")
    private UUID uniqueId;

    @Field(name = "userId")
    private Long userId;

    @Field(name = "orderId")
    private String orderId;

    @Field(name = "eventType")
    private EventType eventType;

    @Field(name = "message")
    private String message;

    @Field(name = "checked")
    private boolean checked = false;

    @Field(name = "createdAt")
    private Instant createdAt = Instant.now();

    public Notification(
        Long userId,
        String orderId,
        String message
    ) {
        this.uniqueId = createUUID();
        this.userId = userId;
        this.orderId = orderId;
        this.eventType = EventType.ORDER;
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Notification that)) {
            return false;
        }
        return get_id().equals(that.get_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id());
    }

    @Override
    public String toString() {
        return String.format("id: %s", _id);
    }
}
