package project.swithme.payment.core.out.client.toss.adapter.response.success;

import lombok.Getter;

@Getter
public class FailureResponse {

    private String code;
    private String message;

    private FailureResponse() {
    }

    public FailureResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
