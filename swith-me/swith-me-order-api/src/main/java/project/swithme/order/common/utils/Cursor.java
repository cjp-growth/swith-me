package project.swithme.order.common.utils;

import java.util.Objects;
import lombok.Getter;

@Getter
public class Cursor {

    private final Long index;
    private final Integer limit;

    private Cursor(
        Long index,
        Integer limit
    ) {
        this.index = index;
        this.limit = limit;
    }

    public static Cursor createCursor(
        Long index,
        Integer limit
    ) {
        return new Cursor(index, limit);
    }

    public boolean isFirstPage() {
        return index == null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Cursor cursor)) {
            return false;
        }
        return getIndex().equals(cursor.getIndex()) && getLimit().equals(cursor.getLimit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getLimit());
    }

    @Override
    public String toString() {
        return String.format("index: %s, limit: %s", index, limit);
    }
}
