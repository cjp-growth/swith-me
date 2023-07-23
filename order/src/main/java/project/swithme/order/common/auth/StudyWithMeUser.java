package project.swithme.order.common.auth;

import java.util.Objects;

public class StudyWithMeUser {

    private final Long userId;

    public StudyWithMeUser(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyWithMeUser that)) return false;
        return getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return userId.toString();
    }
}
