package kg.gov.mtsdr.ubk.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.gov.mtsdr.ubk.common.entity.Application;
import kg.gov.mtsdr.ubk.core.service.ApplicationService;
import kg.gov.mtsdr.ubk.web.dto.ApplicationDto;
import kg.gov.mtsdr.ubk.web.dto.UpdateStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(name = "Application API", description = "Endpoints for managing benefit applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Submit a new application")
    public ResponseEntity<Application> submitApplication(@Valid @RequestBody ApplicationDto applicationDto) {
        Application newApplication = applicationService.submitApplication(applicationDto);
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an application by its ID")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        // In a real app, we'd likely have a service method that returns a DTO
        // and throws a ResourceNotFoundException.
        // applicationService.findById(id)
        return ResponseEntity.ok().build(); // Placeholder
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update the status of an application")
    public ResponseEntity<Void> updateApplicationStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest statusRequest) {
        // applicationService.updateStatus(id, statusRequest.getStatus());
        return ResponseEntity.ok().build(); // Placeholder
    }

    @GetMapping("/search")
    @Operation(summary = "Search for applications")
    public ResponseEntity<Page<Application>> searchApplications(
            @RequestParam(required = false) String pin,
            Pageable pageable) {
        // Page<Application> results = applicationService.search(pin, pageable);
        return ResponseEntity.ok().build(); // Placeholder
    }
}
