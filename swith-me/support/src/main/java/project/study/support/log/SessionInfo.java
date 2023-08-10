package project.study.support.log;

import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import lombok.Getter;

@Getter
public class SessionInfo {

    private long creationTime;
    private String sessionId;
    private long lastAccessedTime;

    public SessionInfo(HttpSession httpSession) {
        this.sessionId = httpSession.getId();
        this.creationTime = httpSession.getCreationTime();
        this.lastAccessedTime = httpSession.getLastAccessedTime();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SessionInfo that)) {
            return false;
        }
        return getSessionId().equals(that.getSessionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreationTime(), getSessionId(), getLastAccessedTime());
    }

    @Override
    public String toString() {
        return String.format(
            "[creationTime: %s, sessionId: %s, lastAccessedTime: %s]",
            creationTime, sessionId, lastAccessedTime
        );
    }
}
