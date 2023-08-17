package project.swithme.order.test.arch;

public interface ArchTestConst {

    // Package
    String PRESENTATION_LAYER_PACKAGE = "..presentation..";
    String FACADE_LAYER_PACKAGE = "..facade..";
    String QUERY_SERVICE_LAYER_PACKAGE = "..application.query..";
    String DAO_LAYER_PACKAGE = "..dao..";
    String TEST_PACKAGE = "project/swithme/order/test";

    // PresentationLayer
    String ORDER_CREATE_CONTROLLER = "OrderCreateAPI";

    // FacadeLayer
    String ORDER_FACADE = "OrderFacade";

    // ApplicationLayer
    String ORDER_USE_CASE = "OrderUseCase";
    String ORDER_QUERY_USE_CASE = "OrderQueryUseCase";

    // Class
    String ADAPTER = "Adapter";
    String SERVICE = "Service";
    String DAO = "Dao";
}
