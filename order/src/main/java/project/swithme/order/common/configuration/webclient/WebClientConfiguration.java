package project.swithme.order.common.configuration.webclient;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;
import static java.time.Duration.ofMillis;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY;

@Configuration
public class WebClientConfiguration {

    @Value("${product-server}")
    private String baseUrl;

    @Bean
    public ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setShutdownTimeout(Duration.ofSeconds(3));
        factory.setUseGlobalResources(false);
        return factory;
    }

    @Bean
    public WebClient webClient() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(VALUES_ONLY);

        // https://www.baeldung.com/spring-webflux-timeout
        HttpClient httpClient = HttpClient.create()
                .option(CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(ofMillis(5000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, SECONDS)));
        return WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
