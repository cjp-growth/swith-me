package project.swithme.order.core.web.order.presentation.response;

import java.util.List;

public class PayGroupsResponse {

    private final List<String> payGroups;

    public PayGroupsResponse(List<String> payGroups) {
        this.payGroups = payGroups;
    }

    public List<String> getPayGroups() {
        return payGroups;
    }

    @Override
    public String toString() {
        return payGroups.toString();
    }
}
