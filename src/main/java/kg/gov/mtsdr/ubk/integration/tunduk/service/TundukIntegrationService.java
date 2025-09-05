package kg.gov.mtsdr.ubk.integration.tunduk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TundukIntegrationService {

    // In a real application, you might inject a map of all TundukAdapter beans
    // private final Map<String, TundukAdapter> adapters;

    public void someBusinessLogicThatRequiresTunduk() {
        log.info("Executing business logic that requires Tunduk integration.");
        // Example of how it could be used:
        // GetPersonInfoRequest request = new GetPersonInfoRequest();
        // request.setPin("12345678901234");
        // GetPersonInfoAdapter adapter = (GetPersonInfoAdapter) adapters.get("get-person-info");
        // GetPersonInfoResponse response = adapter.execute(request);
        // ... do something with the response ...
    }
}
