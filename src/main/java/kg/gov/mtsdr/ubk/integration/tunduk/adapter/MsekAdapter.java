package kg.gov.mtsdr.ubk.integration.tunduk.adapter;

import kg.gov.mtsdr.ubk.integration.tunduk.client.XRoadClient;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.msek.GetDisabilityInfoRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.msek.GetDisabilityInfoResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.mapper.MsekMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MsekAdapter {

    private final XRoadClient xRoadClient;

    public String getDisabilityInfo(String pin) {
        GetDisabilityInfoRequest request = new GetDisabilityInfoRequest(pin);
        GetDisabilityInfoResponse response = xRoadClient.call("service-7", request, GetDisabilityInfoResponse.class);
        return MsekMapper.toDomain(response);
    }
}
