package project.swithme.domain.common;

import java.util.Objects;
import lombok.Getter;

@Getter
public class StudyWithMeUser {

    private final Long userId;

    public StudyWithMeUser(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof StudyWithMeUser that)) {
            return false;
        }
        return getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return String.format("userId: %s", userId);
    }
}
