package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetActualAddressResponse extends TundukResponse {

    private String region;
    private String district;
    private String city;
    private String street;
    private String houseNumber;
}
