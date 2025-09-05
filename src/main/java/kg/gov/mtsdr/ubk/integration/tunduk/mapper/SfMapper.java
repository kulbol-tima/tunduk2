package kg.gov.mtsdr.ubk.integration.tunduk.mapper;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.sf.GetPensionInfoResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.sf.GetWorkPeriodsResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SfMapper {

    public static String toDomain(GetPensionInfoResponse response) {
        // Placeholder
        return "Pension info: " + response.getPensionAmount();
    }

    public static String toDomain(GetWorkPeriodsResponse response) {
        // Placeholder
        return "Found " + response.getWorkPeriods().size() + " work periods.";
    }
}
