package project.swithme.payment.common.mockresponse;

import java.util.List;

public class CancelsResponse {

    private List<CancelResponse> cancels;

    private CancelsResponse() {
    }

    public CancelsResponse(List<CancelResponse> cancels) {
        this.cancels = cancels;
    }

    public List<CancelResponse> getCancels() {
        return cancels;
    }

    @Override
    public String toString() {
        return cancels.toString();
    }
}
