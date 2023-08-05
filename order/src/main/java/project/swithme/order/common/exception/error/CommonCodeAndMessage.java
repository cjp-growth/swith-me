package project.swithme.order.common.exception.error;

public enum CommonCodeAndMessage implements CodeAndMessage {
    BAD_REQUEST(
        400,
        "올바른 값을 입력해주세요.",
        "Please enter a valid value."
    );

    private final int statusCode;
    private final String krErrorMessage;
    private final String engErrorMessage;

    CommonCodeAndMessage(
        int statusCode,
        String krErrorMessage,
        String engErrorMessage
    ) {
        this.statusCode = statusCode;
        this.krErrorMessage = krErrorMessage;
        this.engErrorMessage = engErrorMessage;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getKrErrorMessage() {
        return krErrorMessage;
    }

    @Override
    public String getEngErrorMessage() {
        return engErrorMessage;
    }
}
