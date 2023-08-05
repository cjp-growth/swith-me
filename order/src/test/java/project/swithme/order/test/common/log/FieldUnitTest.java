package project.swithme.order.test.common.log;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.order.common.aop.log.Field;

@DisplayName("[UnitTest] 필드 단위 테스트")
class FieldUnitTest {

    @Test
    @DisplayName("쿠기가 null이라면 캐싱된 값이 반환된다.")
    void cookie_cache_test() {
        assertNotNull(Field.of(null));
    }

    @Test
    @DisplayName("필드명이 null이라면 IllegalArgumentException이 발생한다.")
    void field_name_test() {
        assertThatThrownBy(() -> Field.of(null, "Hello"))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("필드명을 입력해주세요.");
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Field field = Field.of("name", "JYP");
        Field sameField = Field.of("name", "JYP");
        Field otherField = Field.of("name", "CJY");

        assertTrue(field.equals(sameField));
        assertTrue(!field.equals(otherField));
        assertFalse("1".equals(field));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Field field = Field.of("name", "JYP");
        Field sameField = Field.of("name", "JYP");
        Field otherField = Field.of("name", "CJY");

        assertTrue(field.hashCode() == sameField.hashCode());
        assertFalse(field.hashCode() == otherField.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Field field = Field.of("name", "JYP");
        String expected = String.format(
            "key: %s, value: %s",
            field.getName(), field.getValue()
        );

        assertEquals(expected, field.toString());
    }
}
