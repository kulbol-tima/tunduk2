package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetPersonDataResponse extends TundukResponse {

    private String fullName;
    private String passportNumber;
    private LocalDate dateOfBirth;
    private String citizenship;
}
