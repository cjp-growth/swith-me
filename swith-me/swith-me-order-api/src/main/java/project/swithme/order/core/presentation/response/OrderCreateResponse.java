package project.swithme.order.core.presentation.response;

public record OrderCreateResponse(Long orderId) {

    @Override
    public String toString() {
        return orderId.toString();
    }
}
