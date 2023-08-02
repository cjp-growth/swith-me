package project.swithme.order.common.testhelper.payment.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import project.swithme.order.core.web.payment.out.adapter.response.CancelResponse;
import project.swithme.order.core.web.payment.out.adapter.response.CancelsResponse;

public final class PaymentApiResponseFixture {

    private PaymentApiResponseFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static CancelsResponse getCancelsResponse() throws JsonProcessingException {
        String file = getResource("data/response/toss-cancel.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<CancelResponse> responses = List.of(
            objectMapper.readValue(file, CancelResponse.class)
        );
        return new CancelsResponse(responses);
    }

    private static String getResource(String path) {
        return new BufferedReader(getInputStreamReader(path))
            .lines()
            .collect(Collectors.joining("\n"));
    }

    private static InputStreamReader getInputStreamReader(String path) {
        return new InputStreamReader(getMockResourceAsStream(path), StandardCharsets.UTF_8);
    }

    private static InputStream getMockResourceAsStream(String path) {
        return PaymentApiResponseFixture.class.getClassLoader().getResourceAsStream(path);
    }
}
