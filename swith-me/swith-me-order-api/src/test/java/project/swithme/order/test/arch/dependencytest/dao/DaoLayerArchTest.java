package project.swithme.order.test.arch.dependencytest.dao;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static project.swithme.order.test.arch.ArchTestConst.DAO_LAYER_PACKAGE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_FACADE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_QUERY_USE_CASE;
import static project.swithme.order.test.arch.ArchTestConst.ORDER_USE_CASE;
import static project.swithme.order.test.arch.ArchTestConst.QUERY_SERVICE_LAYER_PACKAGE;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.DisplayName;
import project.swithme.order.common.annotation.ArchTestDescription;

@AnalyzeClasses(packages = "project.swithme.order.core")
@DisplayName("[ArchTest] Dao 계층 테스트")
class DaoLayerArchTest {

    @ArchTest
    @ArchTestDescription(
        target = ORDER_FACADE,
        contents = "주문 데이터 조회 접근 계층은 주문 애플리케이션 조회 계층을 통해서만 접근할 수 있다."
    )
    LayeredArchitecture orderQueryDaoLayerAccessTest = layeredArchitecture()
        .layer(DAO_LAYER_PACKAGE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)
        .layer(ORDER_USE_CASE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)
        .layer(ORDER_QUERY_USE_CASE).definedBy(QUERY_SERVICE_LAYER_PACKAGE)

        .whereLayer(ORDER_USE_CASE).mayOnlyBeAccessedByLayers(DAO_LAYER_PACKAGE)
        .whereLayer(ORDER_QUERY_USE_CASE).mayOnlyBeAccessedByLayers(DAO_LAYER_PACKAGE);
}
