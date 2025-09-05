package kg.gov.mtsdr.ubk.integration.tunduk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
@Slf4j
public class SecurityCredentialsConfig {

    @Value("${tunduk.security.keystore-path:}")
    private Resource keystoreResource;

    @Value("${tunduk.security.keystore-password:}")
    private String keystorePassword;

    @Value("${tunduk.security.truststore-path:}")
    private Resource truststoreResource;

    @Value("${tunduk.security.truststore-password:}")
    private String truststorePassword;

    @Bean
    public SSLContext tundukSslContext() {
        if (!keystoreResource.exists() || !truststoreResource.exists()) {
            log.warn("Keystore or Truststore not found. Defaulting to system SSLContext. THIS IS NOT SECURE FOR PRODUCTION.");
            try {
                return SSLContext.getDefault();
            } catch (Exception e) {
                throw new RuntimeException("Failed to get default SSL context", e);
            }
        }

        try {
            // Load Keystore
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (InputStream ksIs = keystoreResource.getInputStream()) {
                keyStore.load(ksIs, keystorePassword.toCharArray());
            }
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

            // Load Truststore
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            try (InputStream tsIs = truststoreResource.getInputStream()) {
                trustStore.load(tsIs, truststorePassword.toCharArray());
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            // Create SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            log.info("Successfully loaded custom SSLContext for Tunduk integration.");
            return sslContext;

        } catch (Exception e) {
            log.error("Failed to create custom SSL context for Tunduk integration", e);
            throw new RuntimeException("Failed to create custom SSL context", e);
        }
    }
}
