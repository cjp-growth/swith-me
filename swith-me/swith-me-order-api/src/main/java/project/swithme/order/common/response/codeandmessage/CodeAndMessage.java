package project.swithme.order.common.response.codeandmessage;

public interface CodeAndMessage {

    int getStatusCode();

    String getKrErrorMessage();

    String getEngErrorMessage();
}
