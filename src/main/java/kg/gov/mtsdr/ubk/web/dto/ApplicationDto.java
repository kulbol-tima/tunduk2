package kg.gov.mtsdr.ubk.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicationDto {

    private Long id;
    private String registrationNumber;
    private LocalDate applicationDate;
    private String applicantFullName;
    private String applicantPin;
    private String address;
    private String phone;
    private String passportSeries;
    private String passportNumber;
    private String familyStatus;
    private Boolean previousBenefit;
    private String status;
}
