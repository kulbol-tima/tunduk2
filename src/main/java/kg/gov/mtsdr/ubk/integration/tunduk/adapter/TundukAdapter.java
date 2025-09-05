package kg.gov.mtsdr.ubk.integration.tunduk.adapter;

import kg.gov.mtsdr.ubk.integration.tunduk.client.XRoadClient;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.request.TundukRequest;
import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TundukAdapter<T extends TundukResponse, R extends TundukRequest> {

    @Autowired
    private XRoadClient xRoadClient;

    public abstract String getServiceName();

    public T execute(R request, Class<T> responseType) {
        return xRoadClient.call(getServiceName(), request, responseType);
    }
}
