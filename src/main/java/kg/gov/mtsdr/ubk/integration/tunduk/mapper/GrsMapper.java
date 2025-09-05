package kg.gov.mtsdr.ubk.integration.tunduk.mapper;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs.GetActualAddressResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs.GetPersonDataResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs.GetVehicleInfoResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GrsMapper {

    public static String toDomain(GetPersonDataResponse response) {
        // Placeholder: In a real app, this would map to a domain object
        return "Person: " + response.getFullName();
    }

    public static String toDomain(GetActualAddressResponse response) {
        // Placeholder
        return "Address: " + response.getCity() + ", " + response.getStreet();
    }

    public static String toDomain(GetVehicleInfoResponse response) {
        // Placeholder
        return "Found " + response.getVehicles().size() + " vehicles.";
    }
}
