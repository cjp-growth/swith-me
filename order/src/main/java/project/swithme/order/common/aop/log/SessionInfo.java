package project.swithme.order.common.aop.log;

import jakarta.servlet.http.HttpSession;
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
    public String toString() {
        return String.format(
            "[creationTime: %s, sessionId: %s, lastAccessedTime: %s]",
            creationTime, sessionId, lastAccessedTime
        );
    }
}
