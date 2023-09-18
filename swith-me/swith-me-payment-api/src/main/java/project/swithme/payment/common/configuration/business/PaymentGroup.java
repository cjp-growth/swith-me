package project.swithme.payment.common.configuration.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.study.support.exception.InvalidParameterException;
import project.study.support.log.Field;
import project.swithme.domain.core.order.entity.PayGroup;

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
            .toList();
    }

    private Map<String, List<String>> extractPayTypeMap(List<PayGroup> payGroup) {
        Map<String, List<String>> payTypesMapGroupByPayGroup = new HashMap<>();

        for (PayGroup eachGroup : payGroup) {
            List<String> payType = PayGroup.findPayTypes(eachGroup);
            payTypesMapGroupByPayGroup.put(eachGroup.name(), payType);
        }
        return payTypesMapGroupByPayGroup;
    }

    public List<String> getPayGroups() {
        return new ArrayList<>(payGroups);
    }

    public List<String> getPayTypes(String payType) {
        if (payType.isBlank() || !payGroups.contains(payType)) {
            throw new InvalidParameterException(List.of(Field.of("payType", payType)));
        }
        return payTypeMap.get(payType);
    }
}
