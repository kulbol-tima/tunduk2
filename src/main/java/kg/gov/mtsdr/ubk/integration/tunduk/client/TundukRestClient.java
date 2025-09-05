package kg.gov.mtsdr.ubk.integration.tunduk.client;

import kg.gov.mtsdr.ubk.common.exception.IntegrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class TundukRestClient {

    private final RestTemplate tundukRestTemplate;

    public <T, R> T post(String url, R requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<R> entity = new HttpEntity<>(requestBody, headers);

        try {
            log.debug("Sending POST request to URL: {}", url);
            ResponseEntity<T> response = tundukRestTemplate.exchange(url, HttpMethod.POST, entity, responseType);
            log.debug("Received response with status: {}", response.getStatusCode());
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("HTTP Client Error: {} for URL: {}. Response body: {}", e.getStatusCode(), url, e.getResponseBodyAsString());
            throw new IntegrationException("Tunduk service returned an error: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            log.error("An unexpected error occurred while calling Tunduk service at URL: {}", url, e);
            throw new IntegrationException("Failed to call Tunduk service", e);
        }
    }
}
