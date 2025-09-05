package kg.gov.mtsdr.ubk.integration.tunduk.mapper;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.isrt.GetEmploymentStatusResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IsrtMapper {

    public static String toDomain(GetEmploymentStatusResponse response) {
        // Placeholder
        return "Is employed: " + response.isEmployed();
    }
}
