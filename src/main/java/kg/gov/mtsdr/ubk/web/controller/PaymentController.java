package kg.gov.mtsdr.ubk.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.gov.mtsdr.ubk.common.entity.Payment;
import kg.gov.mtsdr.ubk.core.repository.PaymentRepository;
import kg.gov.mtsdr.ubk.core.service.PaymentService;
import kg.gov.mtsdr.ubk.web.dto.PaymentGenerationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payment API", description = "Endpoints for managing payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository; // For reads

    @GetMapping
    @Operation(summary = "Get a paginated list of payments")
    public ResponseEntity<Page<Payment>> getPayments(Pageable pageable) {
        Page<Payment> payments = paymentRepository.findAll(pageable);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generate payments for a specific period")
    public ResponseEntity<Void> generatePayments(@Valid @RequestBody PaymentGenerationRequest request) {
        paymentService.generatePaymentOrders(request.getPeriod());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reports")
    @Operation(summary = "Get payment reports")
    public ResponseEntity<String> getPaymentReports() {
        // Placeholder for reporting logic
        return ResponseEntity.ok("Report data would be here.");
    }
}
