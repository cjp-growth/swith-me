package project.swithme.order.common.exception.error;

public interface CodeAndMessage {

    int getStatusCode();

    String getErrorCode();

    String getKrErrorMessage();

    String getEngErrorMessage();
}
