package kg.gov.mtsdr.ubk.common.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"applicationId", "payments"})
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private Application applicationId;

    @NotNull
    @Column(nullable = false)
    private BigDecimal benefitAmount;

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal regionCoefficient;

    private BigDecimal additionalCoefficient;

    private BigDecimal bonus;

    @NotNull
    private String status;

    @OneToMany(mappedBy = "recipientId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return id != null && Objects.equals(id, recipient.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
