package project.swithme.order.core.web.order.presentation.response;

import java.util.List;

public record PayGroupResponse(
        List<String> payGroups
) {
    @Override
    public String toString() {
        return payGroups.toString();
    }
}
