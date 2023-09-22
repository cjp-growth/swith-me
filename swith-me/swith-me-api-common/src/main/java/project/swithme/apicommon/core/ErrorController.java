package project.swithme.apicommon.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.codeandmessage.common.CommonErrorCodeAndMessage;
import project.swithme.apicommon.common.error.ErrorCodeSpec;
import project.swithme.apicommon.common.error.ErrorCodeSpecs;
import project.study.support.codeandmessage.order.OrderErrorCodeAndMessage;
import project.study.support.codeandmessage.payment.PaymentErrorCodeAndMessage;

@RestController
@RequestMapping("/api/specs/errors")
public class ErrorController {

    @GetMapping("/common")
    public ResponseEntity<ErrorCodeSpecs> getCommonErrorCodes() {
        List<CommonErrorCodeAndMessage> codeAndMessages =
            Arrays.stream(CommonErrorCodeAndMessage.values())
                .toList();

        Map<String, ErrorCodeSpec> errorCodes = new HashMap<>();

        for (CommonErrorCodeAndMessage codeAndMessage : codeAndMessages) {
            errorCodes.put(codeAndMessage.getErrorCode(), new ErrorCodeSpec(codeAndMessage));
        }
        return ResponseEntity.ok(new ErrorCodeSpecs(errorCodes));
    }

    @GetMapping("/orders")
    public ResponseEntity<ErrorCodeSpecs> getOrderErrorCodes() {
        List<OrderErrorCodeAndMessage> codeAndMessages =
            Arrays.stream(OrderErrorCodeAndMessage.values())
                .toList();

        Map<String, ErrorCodeSpec> errorCodes = new HashMap<>();

        for (OrderErrorCodeAndMessage codeAndMessage : codeAndMessages) {
            errorCodes.put(codeAndMessage.getErrorCode(), new ErrorCodeSpec(codeAndMessage));
        }
        return ResponseEntity.ok(new ErrorCodeSpecs(errorCodes));
    }

    @GetMapping("/payments")
    public ResponseEntity<ErrorCodeSpecs> getPaymentErrorCodes() {
        List<PaymentErrorCodeAndMessage> codeAndMessages =
            Arrays.stream(PaymentErrorCodeAndMessage.values())
                .toList();

        Map<String, ErrorCodeSpec> errorCodes = new HashMap<>();

        for (PaymentErrorCodeAndMessage codeAndMessage : codeAndMessages) {
            errorCodes.put(codeAndMessage.getErrorCode(), new ErrorCodeSpec(codeAndMessage));
        }
        return ResponseEntity.ok(new ErrorCodeSpecs(errorCodes));
    }
}
