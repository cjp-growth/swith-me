package project.swithme.order.test;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.restassured.RestAssuredOperationPreprocessorsConfigurer;
import project.swithme.order.common.annotation.IntegrationTest;
import project.swithme.order.common.persistence.PersistenceHelper;

@IntegrationTest
public abstract class IntegrationTestBase {

    private static final String SCHEMA = "www.study-with-me.com";

    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected PersistenceHelper persistenceHelper;

    protected RequestSpecification specification;
    @Autowired
    private RdbInitializationConfiguration databaseInitialization;

    protected IntegrationTestBase() {
        initRestAssureConfiguration();
    }

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        databaseInitialization.truncateAllEntity();

        OperationPreprocessor operationPreprocessor = modifyUris()
            .host(SCHEMA)
            .removePort();

        RestAssuredOperationPreprocessorsConfigurer restDocumentationFilter = documentationConfiguration(
            restDocumentation)
            .operationPreprocessors()
            .withRequestDefaults(operationPreprocessor, prettyPrint())
            .withResponseDefaults(prettyPrint());

        this.specification = new RequestSpecBuilder()
            .setPort(port)
            .addFilter(restDocumentationFilter)
            .build();
    }

    private void initRestAssureConfiguration() {
        objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        RestAssured.config = new RestAssuredConfig().objectMapperConfig(
            new ObjectMapperConfig().jackson2ObjectMapperFactory((clazz, charset) -> objectMapper)
        );
    }
}