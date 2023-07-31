package project.swithme.order.test.payment.unittest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[UnitTest] 시간 포맷 변환 단위 테스트")
class StringTimeConvertUnitTest {

    @Test
    @DisplayName("`yyyy-MM-dd'T'HH:mm:ssXXX` 형식으로 문자열이 오면 이를 Instant로 변환할 수 있다.")
    void time_format_change_test() {
        String temp = "2022-01-01T00:00:00+09:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(temp, formatter);

        assertNotNull(offsetDateTime.toInstant());
    }
}
