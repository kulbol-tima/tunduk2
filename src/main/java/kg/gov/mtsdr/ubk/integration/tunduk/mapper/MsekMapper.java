package kg.gov.mtsdr.ubk.integration.tunduk.mapper;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.msek.GetDisabilityInfoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MsekMapper {

    public static String toDomain(GetDisabilityInfoResponse response) {
        // Placeholder
        return "Disability group: " + response.getDisabilityGroup();
    }
}
