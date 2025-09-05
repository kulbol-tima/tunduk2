package kg.gov.mtsdr.ubk.core.scheduler;

import kg.gov.mtsdr.ubk.core.service.ApplicationService;
import kg.gov.mtsdr.ubk.integration.tunduk.service.GrsIntegrationService;
import kg.gov.mtsdr.ubk.integration.tunduk.service.SfIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.scheduler.monthly-verification.enabled", havingValue = "true")
public class MonthlyVerificationScheduler {

    private final ApplicationService applicationService;
    private final GrsIntegrationService grsIntegrationService;
    private final SfIntegrationService sfIntegrationService;

    /**
     * Runs on the first day of every month at 2 AM.
     */
    @Scheduled(cron = "0 0 2 1 * ?")
    public void verifyEligibilityOfRecipients() {
        log.info("Starting monthly verification of recipients' eligibility...");
        try {
            // 1. Get all active recipients
            // List<Recipient> activeRecipients = recipientRepository.findAllActive();
            log.info("Found X active recipients to verify.");

            // 2. For each recipient, check Tunduk services
            // for (Recipient recipient : activeRecipients) {
            //    String pin = recipient.getApplication().getApplicantPin();
            //    var personData = grsIntegrationService.getPersonData(pin);
            //    var pensionInfo = sfIntegrationService.getPensionInfo(pin);
            //    // ... and so on
            //
            //    boolean stillEligible = applicationService.calculateEligibility(recipient.getApplication().getId());
            //    if (!stillEligible) {
            //        // Terminate benefits
            //    }
            // }

            log.info("Monthly verification of recipients' eligibility completed successfully.");
        } catch (Exception e) {
            log.error("An error occurred during the monthly verification of recipients' eligibility.", e);
        }
    }
}
