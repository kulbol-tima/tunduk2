package kg.gov.mtsdr.ubk.integration.tunduk.client;

import kg.gov.mtsdr.ubk.common.exception.IntegrationException;
import kg.gov.mtsdr.ubk.integration.tunduk.config.TundukConfig;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.TundukRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class XRoadClient {

    private final TundukRestClient restClient;
    private final TundukConfig tundukConfig;

    @Retryable(
            value = { IntegrationException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public <T extends TundukResponse, R extends TundukRequest> T call(String serviceName, R request, Class<T> responseType) {
        log.info("Calling Tunduk service '{}' with request ID: {}", serviceName, request.getRequestId());

        TundukConfig.ServiceProps serviceProps = tundukConfig.getServices().get(serviceName);
        if (serviceProps == null) {
            throw new IntegrationException("Configuration for service '" + serviceName + "' not found.");
        }

        // The RestTemplate interceptor handles the headers, this is just an example of how it could be done here
        // if more dynamic headers were needed. The URL construction is important.
        String url = buildUrl(serviceProps);

        T response = restClient.post(url, request, responseType);
        if (response != null) {
            response.setCorrelationId(request.getRequestId());
        }
        return response;
    }

    private String buildUrl(TundukConfig.ServiceProps serviceProps) {
        // In a real X-Road setup, the URL path often includes service identifiers.
        // Example: http://security-server/r1/GOV/12345678/SUB/myService/v1
        return String.format("%s/%s/%s/%s/%s/%s",
                tundukConfig.getSecurityServer().getUrl(),
                serviceProps.getMemberClass(),
                serviceProps.getMemberCode(),
                serviceProps.getSubsystemCode(),
                serviceProps.getServiceCode(),
                serviceProps.getServiceVersion()
        );
    }
}
