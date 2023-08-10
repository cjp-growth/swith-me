package project.swithme.payment.core.out.adapter.response;

import java.util.List;
import lombok.Getter;

@Getter
public class CancelsResponse {

    private List<CancelResponse> cancels;

    private CancelsResponse() {
    }

    public CancelsResponse(List<CancelResponse> cancels) {
        this.cancels = cancels;
    }

    @Override
    public String toString() {
        return cancels.toString();
    }
}
