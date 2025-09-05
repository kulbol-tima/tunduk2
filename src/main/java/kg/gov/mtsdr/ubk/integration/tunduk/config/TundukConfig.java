package kg.gov.mtsdr.ubk.integration.tunduk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "tunduk")
@Data
@EnableRetry
public class TundukConfig {

    private String xroadInstance;
    private SecurityServer securityServer;
    private Client client;
    private Map<String, ServiceProps> services;

    @Data
    public static class SecurityServer {
        private String url;
    }

    @Data
    public static class Client {
        private String memberClass;
        private String memberCode;
        private String subsystemCode;
    }

    @Data
    public static class ServiceProps {
        private String serviceCode;
        private String serviceVersion;
        private String memberClass;
        private String memberCode;
        private String subsystemCode;
    }
}
