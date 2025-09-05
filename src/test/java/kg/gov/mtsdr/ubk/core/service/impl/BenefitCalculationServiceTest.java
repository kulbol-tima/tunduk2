package kg.gov.mtsdr.ubk.core.service.impl;

import kg.gov.mtsdr.ubk.core.service.data.FamilyData;
import kg.gov.mtsdr.ubk.core.service.data.RegionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class BenefitCalculationServiceTest {

    private BenefitCalculationServiceImpl benefitCalculationService;

    @BeforeEach
    void setUp() {
        benefitCalculationService = new BenefitCalculationServiceImpl();
    }

    @Test
    void testCalculateBenefitAmount() {
        FamilyData familyData = new FamilyData();
        familyData.setNumberOfChildren(2);

        RegionData regionData = new RegionData();
        regionData.setRegionCoefficient(new BigDecimal("1.1"));
        regionData.setAdditionalCoefficient(new BigDecimal("1.0"));
        regionData.setBonus(new BigDecimal("100.00"));

        // Formula: (1200 * 2 * 1.1 * 1.0) + 100 = 2640 + 100 = 2740
        BigDecimal expectedAmount = new BigDecimal("2740.00");

        BigDecimal actualAmount = benefitCalculationService.calculateBenefitAmount(familyData, regionData);

        assertThat(actualAmount).isEqualByComparingTo(expectedAmount);
    }

    @Test
    void testCalculateBenefitForZeroChildren() {
        FamilyData familyData = new FamilyData();
        familyData.setNumberOfChildren(0);
        RegionData regionData = new RegionData(); // Coefficients don't matter

        BigDecimal actualAmount = benefitCalculationService.calculateBenefitAmount(familyData, regionData);

        assertThat(actualAmount).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
