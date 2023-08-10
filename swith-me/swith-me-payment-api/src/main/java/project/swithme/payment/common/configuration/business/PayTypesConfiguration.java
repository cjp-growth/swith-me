package project.swithme.payment.common.configuration.business;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.swithme.domain.core.order.entity.PayGroup;

@Configuration
public class PayTypesConfiguration {

    @Bean
    public PaymentGroup paymentGroup() {
        List<PayGroup> payGroups = List.of(
            PayGroup.CASH,
            PayGroup.CARD
        );
        return new PaymentGroup(payGroups);
    }
}
