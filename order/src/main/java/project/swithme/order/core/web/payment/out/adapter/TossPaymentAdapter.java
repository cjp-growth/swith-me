package project.swithme.order.core.web.payment.out.adapter;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import project.swithme.order.common.configuration.business.payment.TossProperties;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;
import project.swithme.order.core.web.payment.exception.PaymentFailureException;
import project.swithme.order.core.web.payment.out.PaymentPort;
import project.swithme.order.core.web.payment.out.adapter.extractor.PaymentInfoExtractor;
import project.swithme.order.core.web.payment.out.adapter.request.TossPaymentApproveRequest;
import project.swithme.order.core.web.payment.out.adapter.response.TossPaymentApproveResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPaymentAdapter implements PaymentPort {

    private static final String DELIMETER = ":";
    private final TossProperties properties;
    private final RestTemplate restTemplate;
    private final PaymentInfoExtractor paymentInfoExtractor;

    @Override
    public TossPaymentCommand requestApproval(
        String paymentKey,
        String orderId,
        BigDecimal amount
    ) {
        TossPaymentApproveResponse response = getResponse(
            paymentKey,
            orderId,
            amount.longValue()
        );
        return convertToDomainLanguage(response);
    }

    private TossPaymentApproveResponse getResponse(
        String paymentKey,
        String orderId,
        Long amount
    ) {
        try {
            return requestApproval(paymentKey, orderId, amount, restTemplate)
                .getBody();
        } catch (RestClientException e) {
            // TODO. 에러 처리 조금 더 고민
            log.error("");
            throw new RuntimeException();
        }
    }

    private ResponseEntity<TossPaymentApproveResponse> requestApproval(
        String paymentKey,
        String orderId,
        Long amount,
        RestTemplate restTemplate
    ) {
        return restTemplate.exchange(
            createURI(paymentKey),
            HttpMethod.POST,
            createRequestEntity(orderId, amount),
            TossPaymentApproveResponse.class
        );
    }

    private URI createURI(String paymentKey) {
        return UriComponentsBuilder
            .fromUriString(properties.getBaseUrl())
            .path(properties.getPath() + paymentKey)
            .encode()
            .build()
            .expand(paymentKey)
            .toUri();
    }

    private HttpEntity<TossPaymentApproveRequest> createRequestEntity(
        String orderId,
        Long amount
    ) {
        return new HttpEntity<>(
            new TossPaymentApproveRequest(orderId, amount),
            createHeaders()
        );
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        byte[] bytes = createByteArray(properties.getSecretKey());
        httpHeaders.setBasicAuth(new String(bytes));
        return httpHeaders;
    }

    private byte[] createByteArray(String secretKey) {
        return getEncoder().encode((secretKey + DELIMETER)
            .getBytes(StandardCharsets.UTF_8));
    }

    private Encoder getEncoder() {
        return Base64.getEncoder();
    }

    private TossPaymentCommand convertToDomainLanguage(TossPaymentApproveResponse response) {
        if (response == null) {
            throw new PaymentFailureException();
        }
        return paymentInfoExtractor.extractInfo(response);
    }
}
