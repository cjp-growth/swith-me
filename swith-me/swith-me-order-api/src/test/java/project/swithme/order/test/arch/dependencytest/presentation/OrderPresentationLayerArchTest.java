package project.swithme.order.test.arch.dependencytest.presentation;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static project.swithme.order.test.arch.ArchTestConst.FACADE_LAYER_PACKAGE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_CREATE_CONTROLLER;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_FACADE;
import static project.swithme.order.test.arch.ArchTestConst.PRESENTATION_LAYER_PACKAGE;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.common.annotation.ArchTestDescription;
import project.swithme.order.test.arch.TestExcludeConfiguration;

@DisplayName("[ArchTest] PresentationLayer 계층 테스트")
@AnalyzeClasses(
    packages = "project.swithme.order",
    importOptions = TestExcludeConfiguration.class
)
class OrderPresentationLayerArchTest {

    @ArchTest
    @ArchTestDescription(
        target = ORDER_CREATE_CONTROLLER,
        contents = {
            "주문 생성 표면 계층은 다른 계층에서 접근할 수 없다.",
            "주문 퍼사드 계층은 주문 생성 표면 계층을 통해서만 접근할 수 있다."
        }
    )
    LayeredArchitecture orderPresentationLayerAccessTest = layeredArchitecture()
        .layer(ORDER_CREATE_CONTROLLER).definedBy(PRESENTATION_LAYER_PACKAGE)
        .layer(ORDER_FACADE).definedBy(FACADE_LAYER_PACKAGE)

        .whereLayer(ORDER_CREATE_CONTROLLER).mayNotBeAccessedByAnyLayer()
        .whereLayer(ORDER_FACADE).mayOnlyBeAccessedByLayers(ORDER_CREATE_CONTROLLER);
}
