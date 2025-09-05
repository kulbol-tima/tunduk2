package kg.gov.mtsdr.ubk.integration.tunduk.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class TundukRequest {

    private String requestId = UUID.randomUUID().toString();
}
