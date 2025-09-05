package kg.gov.mtsdr.ubk.integration.tunduk.adapter;

import kg.gov.mtsdr.ubk.integration.tunduk.client.XRoadClient;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.grs.GetActualAddressRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.grs.GetPersonDataRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.grs.GetVehicleInfoRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs.GetActualAddressResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs.GetPersonDataResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs.GetVehicleInfoResponse;
import kg.gov.mtsdr.ubk.integration.tunduk.mapper.GrsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrsAdapter {

    private final XRoadClient xRoadClient;

    public String getPersonData(String pin) {
        GetPersonDataRequest request = new GetPersonDataRequest(pin);
        GetPersonDataResponse response = xRoadClient.call("get-person-info", request, GetPersonDataResponse.class);
        return GrsMapper.toDomain(response);
    }

    public String getActualAddress(String pin) {
        GetActualAddressRequest request = new GetActualAddressRequest(pin);
        GetActualAddressResponse response = xRoadClient.call("get-address-info", request, GetActualAddressResponse.class);
        return GrsMapper.toDomain(response);
    }

    public String getVehicleInfo(String pin) {
        GetVehicleInfoRequest request = new GetVehicleInfoRequest(pin);
        GetVehicleInfoResponse response = xRoadClient.call("service-3", request, GetVehicleInfoResponse.class);
        return GrsMapper.toDomain(response);
    }
}
