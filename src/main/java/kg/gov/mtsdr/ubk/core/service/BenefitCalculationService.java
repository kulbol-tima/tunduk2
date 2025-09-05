package kg.gov.mtsdr.ubk.core.service;

import kg.gov.mtsdr.ubk.core.service.data.FamilyData;
import kg.gov.mtsdr.ubk.core.service.data.RegionData;

import java.math.BigDecimal;

public interface BenefitCalculationService {

    /**
     * Calculates the benefit amount based on family and regional data.
     * Formula: УБК = (1200 сом × кол-во детей × районный коэфф × доп коэфф) + надбавка
     * @param familyData Data about the family (e.g., number of children).
     * @param regionData Data about the region (e.g., coefficients, bonus).
     * @return The calculated benefit amount.
     */
    BigDecimal calculateBenefitAmount(FamilyData familyData, RegionData regionData);

    /**
     * Applies regional coefficients to a base amount.
     * @param baseAmount The base amount before applying coefficients.
     * @param regionData The regional data containing coefficients.
     * @return The amount after applying coefficients.
     */
    BigDecimal applyRegionCoefficients(BigDecimal baseAmount, RegionData regionData);
}
