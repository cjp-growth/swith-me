package project.swithme.order.test.arch.extendsruletest.dao;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static project.swithme.order.test.arch.ArchTestConst.DAO;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.OrderApplication;
import project.swithme.order.common.annotation.Description;
import project.swithme.order.core.dao.OrderDao;
import project.swithme.order.test.arch.TestExcludeConfiguration;

@DisplayName("[ArchTest] PresentationLayer 계층 테스트")
@AnalyzeClasses(packagesOf = {
    OrderApplication.class}, importOptions = TestExcludeConfiguration.class)
public class OrderDaoLayerExtendsRuleArchTest {

    @ArchTest
    @Description(content = "Dao의 구현체들은 Dao가 클래스 이름 끝에 붙어야 한다.")
    ArchRule orderDaoImplRule = classes().that().implement(OrderDao.class)
        .should().haveSimpleNameEndingWith(DAO);
}
