package project.swithme.order.common.aop.log;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public class CommonRequestLog {

    private String ipAddress;
    private String requestURI;
    private String method;
    private Map<String, List<String>> parameters;
    private String queryString;
    private List<Field> cookies;
    private SessionInfo sessionInfo;

    public CommonRequestLog(HttpServletRequest servletRequest) {
        this.ipAddress = extractIpAddress(servletRequest);
        this.requestURI = servletRequest.getRequestURI();
        this.method = servletRequest.getMethod();
        this.parameters = extractParameters(servletRequest);
        this.queryString = servletRequest.getQueryString();
        this.cookies = extractCookies(servletRequest);
        this.sessionInfo = extractSession(servletRequest);
    }

    // TODO. API Gateway에서 추출
    private String extractIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private SessionInfo extractSession(HttpServletRequest servletRequest) {
        if (servletRequest.getSession() == null) {
            return null;
        }
        return new SessionInfo(servletRequest.getSession());
    }

    private List<Field> extractCookies(HttpServletRequest servletRequest) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies == null || cookies.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(cookies)
            .map(Field::of)
            .toList();
    }

    private Map<String, List<String>> extractParameters(HttpServletRequest servletRequest) {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        if (parameterMap == null || parameterMap.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, List<String>> parameters = new HashMap<>();
        for (String key : parameterMap.keySet()) {
            parameters.put(key, extractParameterValues(parameterMap.get(key)));
        }
        return parameters;
    }

    private List<String> extractParameterValues(String[] values) {
        if (values.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(values)
            .toList();
    }

    @Override
    public String toString() {
        return String.format(
            "ipAddress: %s, requestURI: %s, method: %s, parameters: %s, queryString: %s, cookies: %s, sessionInfo: %s",
            ipAddress, requestURI, method, parameters, queryString, cookies, sessionInfo
        );
    }
}
