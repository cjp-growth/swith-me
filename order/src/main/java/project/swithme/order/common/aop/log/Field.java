package project.swithme.order.common.aop.log;

import jakarta.servlet.http.Cookie;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Field {

    private static final Field FIELD_CACHE = new Field();

    private String name;
    private Object value;

    private Field() {
    }

    private Field(
        String name,
        Object value
    ) {
        this.name = name;
        this.value = value;
    }

    public static Field of(
        String fieldName,
        Object value
    ) {
        validate(fieldName);
        return new Field(fieldName, value);
    }

    private static void validate(String fieldName) {
        if (fieldName == null || fieldName.isBlank()) {
            throw new IllegalArgumentException("필드명을 입력해주세요.");
        }
    }

    public static Field of(Cookie cookie) {
        if (cookie == null) {
            return FIELD_CACHE;
        }
        return new Field(cookie.getName(), cookie.getValue());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Field field)) {
            return false;
        }
        return getValue().equals(field.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return String.format("key: %s, value: %s", name, value);
    }
}
