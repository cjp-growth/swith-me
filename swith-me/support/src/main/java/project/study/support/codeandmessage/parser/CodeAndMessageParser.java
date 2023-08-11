package project.study.support.codeandmessage.parser;

import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.exception.ErrorMessageParseException;

public interface CodeAndMessageParser {

    CodeAndMessage parseRequestApprovalFailureMessage(
        String errorMessage
    ) throws ErrorMessageParseException;

    CodeAndMessage parsePaymentCancelFailureMessage(
        String errorMessage
    ) throws ErrorMessageParseException;
}
