package kg.gov.mtsdr.ubk.integration.tunduk.dto.response;

import lombok.Data;

@Data
public abstract class TundukResponse {

    private String correlationId; // To correlate with the request ID
}
