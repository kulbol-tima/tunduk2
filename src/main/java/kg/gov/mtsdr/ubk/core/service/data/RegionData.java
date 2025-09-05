package kg.gov.mtsdr.ubk.core.service.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegionData {

    private BigDecimal regionCoefficient;
    private BigDecimal additionalCoefficient;
    private BigDecimal bonus;
    // Other relevant region data can be added here
}
