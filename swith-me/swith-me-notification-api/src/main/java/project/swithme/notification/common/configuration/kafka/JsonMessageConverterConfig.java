package project.swithme.notification.common.configuration.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@Configuration
public class JsonMessageConverterConfig {

    @Bean
    public JsonMessageConverter jsonMessageConverter() {
        return new ByteArrayJsonMessageConverter();
    }
}
