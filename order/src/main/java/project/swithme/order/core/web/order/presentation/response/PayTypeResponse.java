package project.swithme.order.core.web.order.presentation.response;

import java.util.List;

public record PayTypeResponse(
        List<String> payTypes
) {
    @Override
    public String toString() {
        return payTypes.toString();
    }
}
