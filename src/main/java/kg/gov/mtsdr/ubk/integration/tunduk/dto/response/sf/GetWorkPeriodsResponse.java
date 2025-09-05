package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.sf;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetWorkPeriodsResponse extends TundukResponse {

    private List<WorkPeriod> workPeriods;

    @Data
    public static class WorkPeriod {
        private String companyName;
        private String position;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
