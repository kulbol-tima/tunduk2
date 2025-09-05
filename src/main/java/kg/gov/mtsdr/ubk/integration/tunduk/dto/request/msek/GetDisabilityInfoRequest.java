package kg.gov.mtsdr.ubk.integration.tunduk.dto.request.msek;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.TundukRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetDisabilityInfoRequest extends TundukRequest {

    private String pin;
}
