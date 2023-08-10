package project.swithme.payment.core.out.adapter.response;

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
