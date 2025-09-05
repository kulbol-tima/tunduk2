package kg.gov.mtsdr.ubk.core.scheduler;

import kg.gov.mtsdr.ubk.core.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.scheduler.payment.enabled", havingValue = "true")
public class PaymentScheduler {

    private final PaymentService paymentService;

    /**
     * Runs on the 5th day of every month at 4 AM to generate payment orders for the current month.
     */
    @Scheduled(cron = "0 0 4 5 * ?")
    public void generateMonthlyPayments() {
        YearMonth currentPeriod = YearMonth.now();
        log.info("Starting monthly payment generation for period {}...", currentPeriod);
        try {
            paymentService.generatePaymentOrders(currentPeriod);
            log.info("Monthly payment generation for period {} completed successfully.", currentPeriod);
        } catch (Exception e) {
            log.error("An error occurred during monthly payment generation for period {}.", currentPeriod, e);
        }
    }
}
