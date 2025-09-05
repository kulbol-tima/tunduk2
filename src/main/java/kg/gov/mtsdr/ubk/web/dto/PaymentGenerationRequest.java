package kg.gov.mtsdr.ubk.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.YearMonth;

@Data
public class PaymentGenerationRequest {

    @NotNull(message = "Period cannot be null")
    private YearMonth period;
}
