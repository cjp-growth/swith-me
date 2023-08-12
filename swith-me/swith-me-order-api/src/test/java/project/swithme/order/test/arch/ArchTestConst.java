package project.swithme.order.test.arch;

public interface ArchTestConst {

    // Package
    String PRESENTATION_LAYER_PACKAGE = "..presentation..";
    String FACADE_LAYER_PACKAGE = "..facade..";
    String SERVICE_LAYER_PACKAGE = "..application..";
    String PRESENTATION_LAYER_VALIDATOR_PACKAGE = "..presentation.validator..";
    String PRESENTATION_LAYER_REQUEST_PACKAGE = "..presentation.request..";
    String PRESENTATION_LAYER_RESPONSE_PACKAGE = "..presentation.response..";
    String FACADE_LAYER_COMMAND_PACKAGE = "..facade.command..";
    String FACADE_LAYER_VALIDATOR_PACKAGE = "..facade.validator..";
    String TEST_PACKAGE = "project/swithme/order/test";

    // PresentationLayer
    String ORDER_CREATE_CONTROLLER = "OrderCreateAPI";
    String ORDER_CREATE_REQUEST = "OrderCreateRequest";
    String ORDER_CREATE_RESPONSE = "OrderCreateResponse";
    String ORDER_CREATE_REQUEST_VALIDATOR = "OrderCreateValidator";
    String ORDER_CREATE_COMMAND = "OrderCreateCommand";
    String ORDER_CREATE_VALIDATOR = "OrderValidator";

    // FacadeLayer
    String ORDER_FACADE = "OrderFacade";

    // ApplicationLayer
    String ORDER_USE_CASE = "OrderUseCase";
    String ORDER_QUERY_USE_CASE = "OrderQueryUseCase";

    // Class
    String ADAPTER = "Adapter";
    String SERVICE = "Service";
}
