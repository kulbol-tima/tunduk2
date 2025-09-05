package kg.gov.mtsdr.ubk.core.service.impl;

import kg.gov.mtsdr.ubk.common.entity.Application;
import kg.gov.mtsdr.ubk.core.repository.ApplicationRepository;
import kg.gov.mtsdr.ubk.web.dto.ApplicationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Test
    void testSubmitApplication() {
        // Given
        ApplicationDto dto = new ApplicationDto();
        dto.setApplicantPin("12345678901234");
        dto.setApplicantFullName("Test User");

        Application savedApplication = new Application();
        savedApplication.setId(1L);
        savedApplication.setApplicantPin(dto.getApplicantPin());

        when(applicationRepository.save(any(Application.class))).thenReturn(savedApplication);

        // When
        Application result = applicationService.submitApplication(dto);

        // Then
        verify(applicationRepository).save(any(Application.class));
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getApplicantPin()).isEqualTo("12345678901234");
    }
}
