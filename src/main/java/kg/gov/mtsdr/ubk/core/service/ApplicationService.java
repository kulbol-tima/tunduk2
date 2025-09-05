package kg.gov.mtsdr.ubk.core.service;

import kg.gov.mtsdr.ubk.common.entity.Application;
import kg.gov.mtsdr.ubk.web.dto.ApplicationDto;

public interface ApplicationService {

    /**
     * Submits a new benefit application.
     * @param applicationDto The DTO containing application data.
     * @return The created Application entity.
     */
    Application submitApplication(ApplicationDto applicationDto);

    /**
     * Processes an existing application.
     * @param id The ID of the application to process.
     */
    void processApplication(Long id);

    /**
     * Calculates the eligibility for benefits for a given application.
     * @param applicationId The ID of the application.
     * @return true if eligible, false otherwise.
     */
    boolean calculateEligibility(Long applicationId);
}
