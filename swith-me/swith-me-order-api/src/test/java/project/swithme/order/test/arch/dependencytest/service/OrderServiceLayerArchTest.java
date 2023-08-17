package project.swithme.order.test.arch.dependencytest.service;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static project.swithme.order.test.arch.ArchTestConst.FACADE_LAYER_PACKAGE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_FACADE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_QUERY_USE_CASE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_USE_CASE;
import static project.swithme.order.test.arch.ArchTestConst.QUERY_SERVICE_LAYER_PACKAGE;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.common.annotation.ArchTestDescription;
import project.swithme.order.test.arch.TestExcludeConfiguration;

@DisplayName("[ArchTest] Application 계층 테스트")
@AnalyzeClasses(
    packages = "project.swithme.order",
    importOptions = TestExcludeConfiguration.class
)
class OrderServiceLayerArchTest {

    @ArchTest
    @ArchTestDescription(
        target = {ORDER_USE_CASE, ORDER_QUERY_USE_CASE},
        contents = "주문 애플리케이션 계층은 퍼사드 계층을 통해서만 접근할 수 있다."
    )
    LayeredArchitecture applicationLayerAccessTest = layeredArchitecture()
        .layer(ORDER_FACADE).definedBy(FACADE_LAYER_PACKAGE)
        .layer(ORDER_USE_CASE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)
        .layer(ORDER_QUERY_USE_CASE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)

        .whereLayer(ORDER_USE_CASE).mayOnlyBeAccessedByLayers(ORDER_FACADE)
        .whereLayer(ORDER_QUERY_USE_CASE).mayOnlyBeAccessedByLayers(ORDER_FACADE);
}
