package kg.gov.mtsdr.ubk.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.gov.mtsdr.ubk.common.entity.Application;
import kg.gov.mtsdr.ubk.core.service.ApplicationService;
import kg.gov.mtsdr.ubk.web.dto.ApplicationDto;
import kg.gov.mtsdr.ubk.web.security.JwtTokenFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApplicationService applicationService;

    @MockBean
    private JwtTokenFilter jwtTokenFilter; // Mock the filter to avoid security complexities in this slice test

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void whenSubmitApplication_withValidDto_thenReturnsCreated() throws Exception {
        // Given
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setApplicantPin("12345678901234");
        applicationDto.setApplicantFullName("Test User");

        Application returnedApplication = new Application();
        returnedApplication.setId(1L);
        returnedApplication.setApplicantPin("12345678901234");

        when(applicationService.submitApplication(any(ApplicationDto.class))).thenReturn(returnedApplication);

        // When & Then
        mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicationDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.applicantPin").value("12345678901234"));
    }
}
