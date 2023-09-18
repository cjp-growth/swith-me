package project.swithme.payment.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import project.swithme.payment.common.annotation.IntegrationTest;

@IntegrationTest
public abstract class IntegrationTestBase extends TestContainerConfiguration {

    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private RdbInitializationConfiguration databaseInitialization;

    protected IntegrationTestBase() {
    }

    @BeforeEach
    void setUp() {
        databaseInitialization.truncateAllEntity();
    }
}
