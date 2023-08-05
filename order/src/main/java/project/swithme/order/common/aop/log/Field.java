package project.swithme.order.common.aop.log;

import jakarta.servlet.http.Cookie;
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
        return new Field(fieldName, value);
    }

    public static Field of(Cookie cookie) {
        if (cookie == null) {
            return FIELD_CACHE;
        }
        return new Field(cookie.getName(), cookie.getValue());
    }

    @Override
    public String toString() {
        return String.format("key: %s, value: %s", name, value);
    }
}
