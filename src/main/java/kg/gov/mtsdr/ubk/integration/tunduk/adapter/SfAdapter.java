package kg.gov.mtsdr.ubk.integration.tunduk.adapter;

import kg.gov.mtsdr.ubk.integration.tunduk.client.XRoadClient;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.sf.GetPensionInfoRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.sf.GetWorkPeriodsRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.sf.GetPensionInfoResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.sf.GetWorkPeriodsResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.mapper.SfMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SfAdapter {

    private final XRoadClient xRoadClient;

    public String getPensionInfo(String pin) {
        GetPensionInfoRequest request = new GetPensionInfoRequest(pin);
        GetPensionInfoResponse response = xRoadClient.call("service-4", request, GetPensionInfoResponse.class);
        return SfMapper.toDomain(response);
    }

    public String getWorkPeriods(String pin) {
        GetWorkPeriodsRequest request = new GetWorkPeriodsRequest(pin);
        GetWorkPeriodsResponse response = xRoadClient.call("service-5", request, GetWorkPeriodsResponse.class);
        return SfMapper.toDomain(response);
    }
}
