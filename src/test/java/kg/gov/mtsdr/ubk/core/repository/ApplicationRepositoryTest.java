package kg.gov.mtsdr.ubk.core.repository;

import kg.gov.mtsdr.ubk.common.entity.Application;
import kg.gov.mtsdr.ubk.config.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class ApplicationRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void testFindByApplicantPin() {
        // Given
        Application app = Application.builder()
                .registrationNumber("REG-123")
                .applicationDate(LocalDate.now())
                .applicantFullName("John Doe")
                .applicantPin("12345678901234")
                .address("Test Address")
                .phone("12345")
                .status("NEW")
                .build();
        applicationRepository.save(app);

        // When
        Optional<Application> found = applicationRepository.findByApplicantPin("12345678901234");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getApplicantFullName()).isEqualTo("John Doe");
    }
}
