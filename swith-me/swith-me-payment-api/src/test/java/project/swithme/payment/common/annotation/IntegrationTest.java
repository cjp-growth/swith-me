package project.swithme.payment.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.restdocs.RestDocumentationExtension;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(RestDocumentationExtension.class)
public @interface IntegrationTest {

}
