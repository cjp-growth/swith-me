package project.swithme.order.test.common.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import project.swithme.order.common.aop.log.SessionInfo;

@DisplayName("[UnitTest] 필드 단위 테스트")
class SessionInfoTest {

    @Test
    @DisplayName("세션이 있다면 객체가 생성된다.")
    void session_info_create_test() {
        assertNotNull(new SessionInfo(new MockHttpSession()));
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        SessionInfo sessionInfo = new SessionInfo(new MockHttpSession());
        SessionInfo otherSessionInfo = new SessionInfo(new MockHttpSession());

        assertTrue(sessionInfo.equals(sessionInfo));
        assertTrue(!sessionInfo.equals(otherSessionInfo));
        assertFalse("1".equals(sessionInfo));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        SessionInfo sessionInfo = new SessionInfo(new MockHttpSession());
        SessionInfo otherSessionInfo = new SessionInfo(new MockHttpSession());

        assertTrue(sessionInfo.hashCode() == sessionInfo.hashCode());
        assertFalse(sessionInfo.hashCode() == otherSessionInfo.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        SessionInfo sessionInfo = new SessionInfo(new MockHttpSession());

        String expected = String.format(
            "[creationTime: %s, sessionId: %s, lastAccessedTime: %s]",
            sessionInfo.getCreationTime(), sessionInfo.getSessionId(),
            sessionInfo.getLastAccessedTime()
        );

        assertEquals(expected, sessionInfo.toString());
    }
}
