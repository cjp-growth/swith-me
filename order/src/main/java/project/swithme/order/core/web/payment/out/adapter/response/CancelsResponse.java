package project.swithme.order.core.web.payment.out.adapter.response;

import java.util.List;
import lombok.Getter;

@Getter
public class CancelsResponse {

    private List<CancelResponse> cancels;

    private CancelsResponse() {
    }
}
