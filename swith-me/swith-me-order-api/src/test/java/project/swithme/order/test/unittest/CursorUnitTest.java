package project.swithme.order.test.unittest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.common.utils.CursorFactory;

@DisplayName("[UnitTest] 커서 단위 테스트")
class CursorUnitTest {

    @Test
    @DisplayName("limit 값이 최대값(25)을 넘어가면 기본 값(10)으로 초기화 된다.")
    void cursor_limit_max_test() {
        Cursor cursor = CursorFactory.createCursor("1", "30");

        assertEquals(10, cursor.getLimit());
    }

    @NullSource
    @ParameterizedTest
    @DisplayName("limit 값이 null이면 기본 값(10)으로 초기화 된다.")
    void cursor_limit_null_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor("1", parameter);

        assertEquals(10, cursor.getLimit());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "-1", "-2", "-3", "-4", "-5", "-6", "-10",
        "124132481048234810482130481204823140923840239850291358120358231058123501235821035"
    })
    @DisplayName("limit 값이 음수면 기본 값(10)으로 초기화 된다.")
    void cursor_negative_limit_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor("10", parameter);

        assertEquals(10, cursor.getLimit());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "fffr", "[]"})
    @DisplayName("올바르지 않은 limit 값이라면 기본 값(10)으로 초기화 된다.")
    void cursor_invalid_limit_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor("10", parameter);

        assertEquals(10, cursor.getLimit());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "fffr", "[]"})
    @DisplayName("올바르지 않은 index 값이라면 null로 초기화 된다.")
    void cursor_invalid_index_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor(parameter, "10");

        assertNull(cursor.getIndex());
    }

    @NullSource
    @ParameterizedTest
    @DisplayName("index 값이 null이면 null로 초기화 된다.")
    void cursor_null_index_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor(parameter, "10");

        assertNull(cursor.getIndex());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "-1", "-2", "-3", "-4", "-5", "-6", "-10",
        "124132481048234810482130481204823140923840239850291358120358231058123501235821035"
    })
    @DisplayName("index 값이 음수라면 기본 값(10)으로 초기화 된다.")
    void cursor_negative_index_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor(parameter, "10");

        assertNull(cursor.getIndex());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100", "1000", "10000"})
    @DisplayName("index, limit 값이 올바르다면 커서 객체가 생성된다.")
    void cursor_valid_index_test(String parameter) {
        Cursor cursor = CursorFactory.createCursor(parameter, "10");

        assertNotNull(cursor.getIndex());
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Cursor cursor = CursorFactory.createCursor("10", "10");
        Cursor sameCursor = CursorFactory.createCursor("10", "10");
        Cursor otherCursor = CursorFactory.createCursor("11", "10");

        assertAll(
            () -> assertTrue(cursor.equals(cursor)),
            () -> assertTrue(cursor.equals(sameCursor)),
            () -> assertFalse(cursor.equals(otherCursor)),
            () -> assertFalse("1".equals(cursor))
        );
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Cursor cursor = CursorFactory.createCursor("10", "10");
        Cursor otherCursor = CursorFactory.createCursor("11", "10");

        assertAll(
            () -> assertTrue(cursor.hashCode() == cursor.hashCode()),
            () -> assertFalse(cursor.hashCode() == otherCursor.hashCode())
        );
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Cursor cursor = CursorFactory.createCursor("10", "10");

        String expected = String.format("index: %s, limit: %s",
            cursor.getIndex(),
            cursor.getLimit()
        );

        assertEquals(expected, cursor.toString());
    }
}
