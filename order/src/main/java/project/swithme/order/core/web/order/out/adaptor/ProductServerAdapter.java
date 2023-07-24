package project.swithme.order.core.web.order.out.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import project.swithme.order.common.response.ProductValidationResponse;
import project.swithme.order.core.web.order.out.ProductClient;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductServerAdapter implements ProductClient {

    private final WebClient webClient;

    @Override
    public void validateProductDetails(
            Long studyCafeId,
            Long productId,
            BigDecimal productPrice,
            Long lockerId,
            BigDecimal lockerPrice
    ) {
        ProductValidationResponse response = null;
    }
}
