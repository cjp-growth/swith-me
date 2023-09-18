package project.swithme.payment.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import project.swithme.payment.common.annotation.IntegrationTest;

@IntegrationTest
public abstract class IntegrationTestBase extends TestContainerConfiguration{

    private static final String SCHEMA = "www.study-with-me.com";

    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private RdbInitializationConfiguration databaseInitialization;

    protected IntegrationTestBase() {
    }

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        databaseInitialization.truncateAllEntity();
    }
}
