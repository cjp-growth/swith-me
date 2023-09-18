package project.swithme.payment.core.presentation.query.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PayGroupsResponse {

    private final List<String> payGroups;

    public PayGroupsResponse(List<String> payGroups) {
        this.payGroups = payGroups;
    }

    @Override
    public String toString() {
        return payGroups.toString();
    }
}
