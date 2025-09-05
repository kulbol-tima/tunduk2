package kg.gov.mtsdr.ubk.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStatusRequest {

    @NotBlank(message = "Status cannot be blank")
    private String status;
}
