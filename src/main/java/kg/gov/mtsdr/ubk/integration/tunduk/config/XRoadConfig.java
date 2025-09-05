package kg.gov.mtsdr.ubk.integration.tunduk.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class XRoadConfig {

    private final TundukConfig tundukConfig;

    @Bean
    public RestTemplate tundukRestTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(30))
                .additionalInterceptors(xRoadHeadersInterceptor())
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor xRoadHeadersInterceptor() {
        return (request, body, execution) -> {
            // These headers should be dynamically set per request based on the target service
            request.getHeaders().set("X-Road-Client", createHeader(
                    tundukConfig.getXroadInstance(),
                    tundukConfig.getClient().getMemberClass(),
                    tundukConfig.getClient().getMemberCode(),
                    tundukConfig.getClient().getSubsystemCode()
            ));
            request.getHeaders().set("X-Road-Id", UUID.randomUUID().toString());
            request.getHeaders().set("X-Road-ProtocolVersion", "4.0");

            return execution.execute(request, body);
        };
    }

    private String createHeader(String... parts) {
        return String.join("/", parts);
    }
}
