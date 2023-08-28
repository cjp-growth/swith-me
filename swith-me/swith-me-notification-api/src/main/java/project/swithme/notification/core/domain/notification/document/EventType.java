package project.swithme.notification.core.domain.notification.document;

import java.util.Arrays;

public enum EventType {
    ORDER(1);

    private final int index;

    EventType(int index) {
        this.index = index;
    }

    public static EventType findEventTypeByIndex(int index) {
        return Arrays.stream(values())
            .filter(eventType -> eventType.index == index)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 이벤트 타입 번호를 넣어주세요."));
    }
}
