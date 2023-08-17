package project.swithme.order.test.arch.dependencytest.facade;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static project.swithme.order.test.arch.ArchTestConst.FACADE_LAYER_PACKAGE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_CREATE_CONTROLLER;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_FACADE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_QUERY_USE_CASE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_USE_CASE;
import static project.swithme.order.test.arch.ArchTestConst.PRESENTATION_LAYER_PACKAGE;
import static project.swithme.order.test.arch.ArchTestConst.QUERY_SERVICE_LAYER_PACKAGE;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.common.annotation.ArchTestDescription;

@AnalyzeClasses(packages = "project.swithme.order.core")
@DisplayName("[ArchTest] Facade 계층 테스트")
class FacadeLayerArchTest {

    @ArchTest
    @ArchTestDescription(
        target = ORDER_FACADE,
        contents = "주문 퍼사드 계층은 주문 생성 표면 계층을 통해서만 접근할 수 있다."
    )
    LayeredArchitecture orderFacadeLayerAccessTest = layeredArchitecture()
        .layer(ORDER_CREATE_CONTROLLER).definedBy(PRESENTATION_LAYER_PACKAGE)
        .layer(ORDER_FACADE).definedBy(FACADE_LAYER_PACKAGE)
        .layer(ORDER_USE_CASE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)
        .layer(ORDER_QUERY_USE_CASE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)

        .whereLayer(ORDER_FACADE).mayOnlyBeAccessedByLayers(ORDER_CREATE_CONTROLLER)
        .whereLayer(ORDER_USE_CASE).mayOnlyBeAccessedByLayers(ORDER_FACADE)
        .whereLayer(ORDER_QUERY_USE_CASE).mayOnlyBeAccessedByLayers(ORDER_FACADE);
}
