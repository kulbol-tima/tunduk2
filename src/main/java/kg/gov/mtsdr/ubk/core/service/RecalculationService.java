package kg.gov.mtsdr.ubk.core.service;

public interface RecalculationService {

    /**
     * Triggers a recalculation for all benefits due to a change in the base value.
     */
    void recalculateByBaseValueChange();

    /**
     * Triggers a recalculation for all benefits due to a change in regional coefficients.
     */
    void recalculateByRegionChange();

    /**
     * Triggers a recalculation for a specific recipient due to a change of address.
     * @param recipientId The ID of the recipient whose benefit needs recalculation.
     */
    void recalculateByAddressChange(Long recipientId);
}
