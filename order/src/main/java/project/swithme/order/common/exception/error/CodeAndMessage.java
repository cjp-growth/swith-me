package project.swithme.order.common.exception.error;

public interface CodeAndMessage {

    int getStatusCode();

    String getKrErrorMessage();

    String getEngErrorMessage();
}
