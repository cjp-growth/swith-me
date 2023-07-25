package project.swithme.order.common.configuration.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.swithme.order.core.domain.order.entity.PayGroup;

import java.util.List;

@Configuration
public class PayConfiguration {

    @Bean
    public PaymentGroup payTypes() {
        List<PayGroup> payGroups = List.of(
                PayGroup.CASH,
                PayGroup.CARD
        );
        return new PaymentGroup(payGroups);
    }
}
