package kg.gov.mtsdr.ubk.core.service.impl;

import kg.gov.mtsdr.ubk.common.entity.Payment;
import kg.gov.mtsdr.ubk.common.entity.Recipient;
import kg.gov.mtsdr.ubk.common.exception.ServiceException;
import kg.gov.mtsdr.ubk.core.repository.PaymentRepository;
import kg.gov.mtsdr.ubk.core.repository.RecipientRepository;
import kg.gov.mtsdr.ubk.core.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RecipientRepository recipientRepository;

    @Override
    @Transactional
    public void generatePaymentOrders(YearMonth period) {
        log.info("Generating payment orders for period: {}", period);
        try {
            // Find all active recipients
            // This is a simplified query, a real one would check status, dates, etc.
            List<Recipient> activeRecipients = recipientRepository.findAll();
            log.info("Found {} active recipients for period {}", activeRecipients.size(), period);

            for (Recipient recipient : activeRecipients) {
                Payment payment = new Payment();
                payment.setRecipientId(recipient);
                payment.setAmount(recipient.getBenefitAmount());
                payment.setPaymentDate(LocalDate.now()); // Or a specific date for the period
                payment.setBankAccount("some-bank-account"); // Placeholder
                payment.setStatus("GENERATED");
                paymentRepository.save(payment);
            }
            log.info("Payment orders for period {} generated successfully.", period);
        } catch (Exception e) {
            log.error("Failed to generate payment orders for period: {}", period, e);
            throw new ServiceException("Error during payment order generation for period: " + period, e);
        }
    }

    @Override
    @Transactional
    public void processPayment(Long paymentId) {
        log.info("Processing payment with ID: {}", paymentId);
        try {
            Payment payment = paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new ServiceException("Payment not found with ID: " + paymentId));

            payment.setStatus("PROCESSED");
            paymentRepository.save(payment);

            log.info("Payment {} processed successfully.", paymentId);
        } catch (Exception e) {
            log.error("Failed to process payment with ID: {}", paymentId, e);
            throw new ServiceException("Error while processing payment with ID: " + paymentId, e);
        }
    }
}
