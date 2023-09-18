package project.swithme.payment.common.testcontainer;

import org.testcontainers.containers.MySQLContainer;

public final class TestContainer {

    private static MySQLContainer<?> mySQLContainer =
        new MySQLContainer<>("mysql:8.0.28");

//    private TestContainer() {
//        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
//    }

    public void start() {
//        String useTestContainer = System.getenv("USE_TESTCONTAINER");
//        if ("true".equals(useTestContainer)) {
            mySQLContainer.start();
//        }
    }

    public void stop() {
        if (mySQLContainer.isRunning()) {
            mySQLContainer.stop();
        }
    }
}
