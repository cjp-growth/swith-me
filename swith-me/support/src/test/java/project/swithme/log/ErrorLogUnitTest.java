package project.swithme.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.study.support.log.ErrorLog;
import project.study.support.log.Field;

@DisplayName("[UnitTest] 필드 단위 테스트")
class ErrorLogUnitTest {

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        ErrorLog errorLog = new ErrorLog(
            "NOT_FOUND",
            404,
            "자원을 찾을 수 없습니다",
            List.of(Field.of("name", "value"))
        );

        String expected = String.format(
            "domain: %s, fields: %s, httpStatus: %s, statusCode: %s, message: %s, time: %s",
            "PAYMENT", errorLog.getErrorFields(), errorLog.getHttpStatus(),
            errorLog.getStatusCode(), errorLog.getMessage(), errorLog.getTime()
        );

        assertEquals(expected, errorLog.toString());
    }
}
