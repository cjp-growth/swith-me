package project.swithme.order.test.arch.namingruletest.out;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static project.swithme.order.test.arch.ArchTestConst.ADAPTER;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.OrderApplication;
import project.swithme.order.common.annotation.Description;
import project.swithme.order.core.out.ProductClient;
import project.swithme.order.test.arch.TestExcludeConfiguration;

@DisplayName("[ArchTest] PresentationLayer 계층 테스트")
@AnalyzeClasses(packagesOf = {
    OrderApplication.class}, importOptions = TestExcludeConfiguration.class)
public class OrderOutLayerNamingRuleArchTest {

    @ArchTest
    @Description(content = "ProductClient의 구현체들은 Adapter가 클래스 이름에 붙어야 한다.")
    ArchRule orderUseCaseImplRule = classes().that().implement(ProductClient.class)
        .should().haveSimpleNameEndingWith(ADAPTER);
}
