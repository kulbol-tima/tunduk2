package kg.gov.mtsdr.ubk.core.service;

import java.time.YearMonth;

public interface PaymentService {

    /**
     * Generates payment orders for a specific period (e.g., a month).
     * @param period The year and month for which to generate payments.
     */
    void generatePaymentOrders(YearMonth period);

    /**
     * Processes a specific payment.
     * @param paymentId The ID of the payment to process.
     */
    void processPayment(Long paymentId);
}
