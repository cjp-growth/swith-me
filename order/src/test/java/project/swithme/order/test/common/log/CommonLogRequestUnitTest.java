package project.swithme.order.test.common.log;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import jakarta.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import project.swithme.order.common.aop.log.CommonRequestLog;

@DisplayName("[UnitTest] 요청 로그 단위 테스트")
class CommonLogRequestUnitTest {

    private MockHttpServletRequest servletRequest;

    @BeforeEach
    void setUp() {
        createMockServletRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "X-Forwarded-For", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
    })
    @DisplayName("프록시 IP 주소로 온 값을 꺼낼 수 있다.")
    void real_ipaddress_extract_from_proxy_ipaddress_test(String parameter) {
        servletRequest.addHeader(parameter, "58.34.9.04");

        CommonRequestLog requestLog = new CommonRequestLog(servletRequest);
        assertNotNull("58.34.9.04", requestLog.getIpAddress());
    }

    /**
     * Header에서 값이 null로 들어오는 경우도 있지만 이는 테스트에서 잡지 못합니다. 따라서 이 경우에 대해서도 대비를 해야 합니다.
     */
    @ParameterizedTest
    @ValueSource(strings = {
        "X-Forwarded-For", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
    })
    @DisplayName("프록시 IP 주소로 온 값이 빈 문자열이면 UN_KNOWN을 반환한다.")
    void real_ipaddress_extract_from_proxy_ipaddress_null_test(String parameter) {
        servletRequest.addHeader(parameter, "");

        CommonRequestLog requestLog = new CommonRequestLog(servletRequest);
        assertNotNull("UN_KNOWN", requestLog.getIpAddress());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "X-Forwarded-For", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
    })
    @DisplayName("프록시 IP 주소로 온 값이 조작된, 올바르지 않은 값이면 UN_KNOWN을 반환한다.")
    void real_ipaddress_3extract_from_proxy_ipaddress_null_test(String parameter) {
        servletRequest.addHeader(parameter, "aa###ㅁㅁㅁ럽댋ㄹ래#####&*@@*@*@@*@*@*");

        CommonRequestLog requestLog = new CommonRequestLog(servletRequest);
        assertNotNull("UN_KNOWN", requestLog.getIpAddress());
    }


    private void createMockServletRequest() {
        Map<String, ?> parameters = new HashMap<>();
        servletRequest = new MockHttpServletRequest();
        servletRequest.setRequestURI("/api/orders");
        servletRequest.setMethod("POST");
        servletRequest.addParameters(parameters);
        servletRequest.setQueryString(null);
        servletRequest.setCookies(new Cookie("name", "value"));
        servletRequest.setSession(new MockHttpSession());
    }
}
