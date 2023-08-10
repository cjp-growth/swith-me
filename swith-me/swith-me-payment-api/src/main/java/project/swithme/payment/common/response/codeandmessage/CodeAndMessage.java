package project.swithme.payment.common.response.codeandmessage;

public interface CodeAndMessage {

    int getStatusCode();

    String getKrErrorMessage();

    String getEngErrorMessage();
}
