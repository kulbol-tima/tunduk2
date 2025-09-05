package kg.gov.mtsdr.ubk.integration.tunduk.dto.response.grs;

import kg.gov.mtsdr.ubk.integration.tunduk.dto.response.TundukResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetVehicleInfoResponse extends TundukResponse {

    private List<Vehicle> vehicles;

    @Data
    public static class Vehicle {
        private String make;
        private String model;
        private int year;
        private String licensePlate;
    }
}
