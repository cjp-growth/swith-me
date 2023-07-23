package project.swithme.order.core.web.order.presentation.response;

public record OrderCreateResponse(Long orderId) {
    @Override
    public String toString() {
        return orderId.toString();
    }
}
