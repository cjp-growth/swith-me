package project.swithme.order.test.arch.namingruletest.service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static project.swithme.order.test.arch.ArchTestConst.SERVICE;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.OrderApplication;
import project.swithme.order.common.annotation.Description;
import project.swithme.order.core.application.OrderQueryUseCase;
import project.swithme.order.core.application.OrderUseCase;
import project.swithme.order.test.arch.TestExcludeConfiguration;

@DisplayName("[ArchTest] PresentationLayer 계층 테스트")
@AnalyzeClasses(packagesOf = {
    OrderApplication.class}, importOptions = TestExcludeConfiguration.class)
public class OrderServiceLayerNamingRuleArchTest {

    @ArchTest
    @Description(content = "OrderUseCase의 구현체들은 Service가 클래스 이름에 붙어야 한다.")
    ArchRule orderUseCaseImplNamingRuleTest = classes().that().implement(OrderUseCase.class)
        .should().haveSimpleNameEndingWith(SERVICE);

    @ArchTest
    @Description(content = "OrderQueryUseCase의 구현체들은 Service가 클래스 이름에 붙어야 한다.")
    ArchRule orderQueryUseCaseImplNamingRuleTest = classes().that()
        .implement(OrderQueryUseCase.class)
        .should().haveSimpleNameEndingWith(SERVICE);
}
