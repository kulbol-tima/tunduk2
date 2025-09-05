package kg.gov.mtsdr.ubk.core.service.impl;

import kg.gov.mtsdr.ubk.core.service.BenefitCalculationService;
import kg.gov.mtsdr.ubk.core.service.data.FamilyData;
import kg.gov.mtsdr.ubk.core.service.data.RegionData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class BenefitCalculationServiceImpl implements BenefitCalculationService {

    private static final BigDecimal BASE_AMOUNT_PER_CHILD = new BigDecimal("1200");

    /**
     * Calculates the benefit amount based on family and regional data.
     * Formula: УБК = (1200 сом × кол-во детей × районный коэфф × доп коэфф) + надбавка
     */
    @Override
    public BigDecimal calculateBenefitAmount(FamilyData familyData, RegionData regionData) {
        log.info("Calculating benefit for family with {} children and region data: {}",
                familyData.getNumberOfChildren(), regionData);

        if (familyData.getNumberOfChildren() <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal baseBenefit = BASE_AMOUNT_PER_CHILD.multiply(BigDecimal.valueOf(familyData.getNumberOfChildren()));
        BigDecimal finalBenefit = applyRegionCoefficients(baseBenefit, regionData);

        log.info("Calculated final benefit amount: {}", finalBenefit);
        return finalBenefit;
    }

    @Override
    public BigDecimal applyRegionCoefficients(BigDecimal baseAmount, RegionData regionData) {
        log.info("Applying regional coefficients to base amount: {}", baseAmount);
        BigDecimal amount = baseAmount;

        if (regionData.getRegionCoefficient() != null) {
            amount = amount.multiply(regionData.getRegionCoefficient());
        }
        if (regionData.getAdditionalCoefficient() != null) {
            amount = amount.multiply(regionData.getAdditionalCoefficient());
        }
        if (regionData.getBonus() != null) {
            amount = amount.add(regionData.getBonus());
        }

        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
