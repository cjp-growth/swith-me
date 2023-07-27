package project.swithme.order.common.configuration.business;

import lombok.Getter;
import project.swithme.order.core.domain.order.entity.PayGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toUnmodifiableList;
import static project.swithme.order.core.domain.order.entity.PayGroup.findPayTypes;

@Getter
public class PaymentGroup {

    private final List<String> payGroups;
    private final Map<String, List<String>> payTypeMap;

    public PaymentGroup(List<PayGroup> payGroup) {
        this.payGroups = extractPayGroups(payGroup);
        this.payTypeMap = extractPayTypeMap(payGroup);
    }

    private List<String> extractPayGroups(List<PayGroup> payGroup) {
        return payGroup.stream()
                .map(Enum::name)
                .collect(toUnmodifiableList());
    }

    private Map<String, List<String>> extractPayTypeMap(List<PayGroup> payGroup) {
        Map<String, List<String>> payTypeMap = new HashMap<>();

        for (PayGroup eachGroup : payGroup) {
            List<String> payType = findPayTypes(eachGroup);
            payTypeMap.put(eachGroup.name(), payType);
        }
        return payTypeMap;
    }

    public List<String> getPayTypes(String payGroup) {
        if (payGroup.isBlank() || !payGroups.contains(payGroup)) {
            throw new IllegalArgumentException("올바른 결제 그룹을 입력해주세요.");
        }
        return payTypeMap.get(payGroup);
    }
}
