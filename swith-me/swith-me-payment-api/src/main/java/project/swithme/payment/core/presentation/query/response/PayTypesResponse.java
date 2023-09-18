package project.swithme.payment.core.presentation.query.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PayTypesResponse {

    private final List<String> payTypes;

    public PayTypesResponse(List<String> payTypes) {
        this.payTypes = payTypes;
    }

    @Override
    public String toString() {
        return payTypes.toString();
    }
}
