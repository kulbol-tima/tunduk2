package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.msek;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetDisabilityInfoResponse extends TundukResponse {

    private boolean hasDisability;
    private int disabilityGroup; // e.g., 1, 2, 3
    private String disabilityCause;
}
