package kg.gov.mtsdr.ubk.integration.tunduk.adapter;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class GrsAdapterTest {

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance().build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("tunduk.security-server.url", wireMockServer::baseUrl);
    }

    @Autowired
    private GrsAdapter grsAdapter;

    @Test
    void testGetPersonData() {
        // Given
        String pin = "12345678901234";
        String mockResponse = "{\"fullName\":\"Test User from Tunduk\",\"passportNumber\":\"ID123\",\"dateOfBirth\":\"1990-01-01\"}";

        wireMockServer.stubFor(post(urlMatching(".*/get-person-info.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(mockResponse)));

        // When
        String result = grsAdapter.getPersonData(pin);

        // Then
        assertThat(result).isEqualTo("Person: Test User from Tunduk");
    }
}
