package kg.gov.mtsdr.ubk.core.service.impl;

import kg.gov.mtsdr.ubk.common.entity.Application;
import kg.gov.mtsdr.ubk.common.exception.ServiceException;
import kg.gov.mtsdr.ubk.core.repository.ApplicationRepository;
import kg.gov.mtsdr.ubk.core.service.ApplicationService;
import kg.gov.mtsdr.ubk.web.dto.ApplicationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Application submitApplication(ApplicationDto applicationDto) {
        log.info("Submitting new application for PIN: {}", applicationDto.getApplicantPin());
        try {
            // In a real scenario, we would use a mapper (e.g., MapStruct)
            Application application = new Application();
            application.setRegistrationNumber(applicationDto.getRegistrationNumber());
            application.setApplicationDate(applicationDto.getApplicationDate());
            application.setApplicantFullName(applicationDto.getApplicantFullName());
            application.setApplicantPin(applicationDto.getApplicantPin());
            application.setAddress(applicationDto.getAddress());
            application.setPhone(applicationDto.getPhone());
            application.setPassportSeries(applicationDto.getPassportSeries());
            application.setPassportNumber(applicationDto.getPassportNumber());
            application.setFamilyStatus(applicationDto.getFamilyStatus());
            application.setPreviousBenefit(applicationDto.getPreviousBenefit());
            application.setStatus("NEW"); // Initial status

            return applicationRepository.save(application);
        } catch (Exception e) {
            log.error("Failed to submit application for PIN: {}", applicationDto.getApplicantPin(), e);
            throw new ServiceException("Error while submitting application", e);
        }
    }

    @Override
    @Transactional
    public void processApplication(Long id) {
        log.info("Processing application with ID: {}", id);
        try {
            Application application = applicationRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Application not found with ID: " + id));

            // Placeholder for business logic
            application.setStatus("PROCESSED");
            applicationRepository.save(application);

            log.info("Application {} processed successfully.", id);
        } catch (Exception e) {
            log.error("Failed to process application with ID: {}", id, e);
            throw new ServiceException("Error while processing application with ID: " + id, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean calculateEligibility(Long applicationId) {
        log.info("Calculating eligibility for application ID: {}", applicationId);
        applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ServiceException("Application not found with ID: " + applicationId));
        // Placeholder for eligibility calculation logic
        return true;
    }
}
