package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.isrt;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetEmploymentStatusResponse extends TundukResponse {

    private boolean isEmployed;
    private String lastKnownEmployer;
}
