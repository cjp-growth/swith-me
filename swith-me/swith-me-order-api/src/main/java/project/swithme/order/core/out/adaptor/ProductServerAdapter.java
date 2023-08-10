package project.swithme.order.core.out.adaptor;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import project.swithme.order.core.out.ProductClient;

@Component
@RequiredArgsConstructor
public class ProductServerAdapter implements ProductClient {

    private final WebClient webClient;

    // TODO. 상품 서버 개발 후 Validation 진행
    @Override
    public void validateProductDetails(
        Long studyCafeId,
        Long productId,
        BigDecimal productPrice,
        Long lockerId,
        BigDecimal lockerPrice
    ) {
    }
}
