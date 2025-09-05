package kg.gov.mtsdr.ubk.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.gov.mtsdr.ubk.common.entity.Recipient;
import kg.gov.mtsdr.ubk.core.repository.RecipientRepository;
import kg.gov.mtsdr.ubk.core.service.RecalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipients")
@RequiredArgsConstructor
@Tag(name = "Recipient API", description = "Endpoints for managing benefit recipients")
public class RecipientController {

    private final RecipientRepository recipientRepository; // Using repo directly for simplicity
    private final RecalculationService recalculationService;

    @GetMapping
    @Operation(summary = "Get a paginated list of recipients")
    public ResponseEntity<Page<Recipient>> getAllRecipients(Pageable pageable) {
        Page<Recipient> recipients = recipientRepository.findAll(pageable);
        return ResponseEntity.ok(recipients);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a recipient by ID")
    public ResponseEntity<Recipient> getRecipientById(@PathVariable Long id) {
        return recipientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/recalculate")
    @Operation(summary = "Trigger a recalculation for a recipient")
    public ResponseEntity<Void> recalculateBenefit(@PathVariable Long id) {
        recalculationService.recalculateByAddressChange(id); // Assuming this type of recalc
        return ResponseEntity.ok().build();
    }
}
