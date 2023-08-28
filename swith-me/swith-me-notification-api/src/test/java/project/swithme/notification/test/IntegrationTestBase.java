package project.swithme.notification.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import project.swithme.notification.core.domain.notification.repository.NotificationRepository;

@Testcontainers
@EmbeddedKafka(ports = 0)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTestBase {

    @Container
    protected static MongoDBContainer mongoDBContainer =
        new MongoDBContainer("mongo:6.0")
            .withExposedPorts(27017);

    @Autowired
    protected NotificationRepository notificationRepository;

    @LocalServerPort
    protected int port;

    protected IntegrationTestBase() {
    }

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    public static void beforeAll() {
        mongoDBContainer.start();
    }

    @AfterAll
    public static void afterAll() {
        mongoDBContainer.close();
    }
}
