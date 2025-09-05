package kg.gov.mtsdr.ubk.core.service.impl;

import kg.gov.mtsdr.ubk.common.entity.Recipient;
import kg.gov.mtsdr.ubk.common.exception.ServiceException;
import kg.gov.mtsdr.ubk.core.repository.RecipientRepository;
import kg.gov.mtsdr.ubk.core.service.BenefitCalculationService;
import kg.gov.mtsdr.ubk.core.service.RecalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecalculationServiceImpl implements RecalculationService {

    private final RecipientRepository recipientRepository;
    private final BenefitCalculationService benefitCalculationService;

    @Override
    @Transactional
    public void recalculateByBaseValueChange() {
        log.info("Starting recalculation for all recipients due to base value change.");
        try {
            List<Recipient> recipients = recipientRepository.findAll();
            // In a real scenario, you would have a more complex logic to get new values
            // and recalculate for each recipient.
            log.info("Found {} recipients to recalculate.", recipients.size());
            // Placeholder for recalculation logic
            log.info("Recalculation due to base value change completed.");
        } catch (Exception e) {
            log.error("Failed to recalculate benefits by base value change", e);
            throw new ServiceException("Error during recalculation by base value change", e);
        }
    }

    @Override
    @Transactional
    public void recalculateByRegionChange() {
        log.info("Starting recalculation for all recipients due to region change.");
        try {
            // Placeholder logic
            log.info("Recalculation due to region change completed.");
        } catch (Exception e) {
            log.error("Failed to recalculate benefits by region change", e);
            throw new ServiceException("Error during recalculation by region change", e);
        }
    }

    @Override
    @Transactional
    public void recalculateByAddressChange(Long recipientId) {
        log.info("Starting recalculation for recipient {} due to address change.", recipientId);
        try {
            Recipient recipient = recipientRepository.findById(recipientId)
                    .orElseThrow(() -> new ServiceException("Recipient not found with ID: " + recipientId));
            // Placeholder for recalculation logic
            log.info("Recalculation for recipient {} completed.", recipientId);
        } catch (Exception e) {
            log.error("Failed to recalculate benefits for recipient: {}", recipientId, e);
            throw new ServiceException("Error during recalculation for recipient: " + recipientId, e);
        }
    }
}
