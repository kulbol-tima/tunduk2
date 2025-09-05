package kg.gov.mtsdr.ubk.core.scheduler;

import kg.gov.mtsdr.ubk.core.service.RecalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.scheduler.recalculation.enabled", havingValue = "true")
public class RecalculationScheduler {

    private final RecalculationService recalculationService;

    /**
     * Runs every day at 3 AM to check for any global value changes.
     * In a real system, this might be triggered by an event rather than a schedule.
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void checkForGlobalRecalculations() {
        log.info("Starting daily check for global recalculations...");
        try {
            // Some logic to determine if a global change (base value, coefficients) has occurred.
            boolean baseValueChanged = false; // Check a config value or database flag
            if (baseValueChanged) {
                log.info("Base value has changed, triggering global recalculation.");
                recalculationService.recalculateByBaseValueChange();
            }

            log.info("Daily check for global recalculations completed successfully.");
        } catch (Exception e) {
            log.error("An error occurred during the daily check for global recalculations.", e);
        }
    }
}
