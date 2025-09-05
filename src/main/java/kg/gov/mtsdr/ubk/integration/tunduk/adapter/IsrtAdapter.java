package kg.gov.mtsdr.ubk.integration.tunduk.adapter;

import kg.gov.mtsdr.ubk.integration.tunduk.client.XRoadClient;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.isrt.GetEmploymentStatusRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.isrt.GetEmploymentStatusResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.mapper.IsrtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IsrtAdapter {

    private final XRoadClient xRoadClient;

    public String getEmploymentStatus(String pin) {
        GetEmploymentStatusRequest request = new GetEmploymentStatusRequest(pin);
        GetEmploymentStatusResponse response = xRoadClient.call("service-6", request, GetEmploymentStatusResponse.class);
        return IsrtMapper.toDomain(response);
    }
}
