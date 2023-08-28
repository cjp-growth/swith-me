package project.swithme.notification.common.utils;

import com.fasterxml.uuid.Generators;
import java.util.UUID;

public final class UUIDGenerators {

    private UUIDGenerators() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static UUID createUUID() {
        return Generators.timeBasedGenerator()
            .generate();
    }
}
