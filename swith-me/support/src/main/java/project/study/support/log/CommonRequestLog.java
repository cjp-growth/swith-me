package project.study.support.log;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class CommonRequestLog {

    private static final String IPV4_PATTERN =
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final Pattern ipV4Pattern = Pattern.compile(IPV4_PATTERN);

    private static final String IPV6_PATTERN =
        "(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|"
            + "([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|"
            + "([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|"
            + "([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|"
            + "[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|"
            + "fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|"
            + "::(ffff(:0{1,4}){0,4}|(:0{1,4}){0,5}:)(25[0-5]|(2[0-4]|1{0,1}[0-9])?[0-9])\\.25[0-5]|"
            + "(2[0-4]|1{0,1}[0-9])?[0-9]\\.(25[0-5]|(2[0-4]|1{0,1}[0-9])?[0-9])\\.25[0-5]|(2[0-4]|"
            + "1{0,1}[0-9])?[0-9])";
    private static final Pattern ipV6Pattern = Pattern.compile(IPV6_PATTERN);

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

    private static boolean isValidIpAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.isBlank()) {
            return false;
        }
        return ipV4Pattern.matcher(ipAddress).matches()
            || ipV6Pattern.matcher(ipAddress).matches();
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
        if (!isValidIpAddress(ip)) {
            return "UN_KNOWN";
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

    private List<String> extractParameterValues(String... values) {
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
