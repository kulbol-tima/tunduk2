package kg.gov.mtsdr.ubk.integration.tunduk.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private String code;
    private String message;
    private String details;
}
