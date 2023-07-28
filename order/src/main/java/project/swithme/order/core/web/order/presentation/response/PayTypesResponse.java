package project.swithme.order.core.web.order.presentation.response;

import java.util.List;

public class PayTypesResponse {

    private final List<String> payTypes;

    public PayTypesResponse(List<String> payTypes) {
        this.payTypes = payTypes;
    }

    public List<String> getPayTypes() {
        return payTypes;
    }

    @Override
    public String toString() {
        return payTypes.toString();
    }
}
