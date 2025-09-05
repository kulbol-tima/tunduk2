package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.sf;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetPensionInfoResponse extends TundukResponse {

    private boolean hasPension;
    private BigDecimal pensionAmount;
    private String pensionType;
}
