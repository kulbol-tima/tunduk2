package kg.gov.mtsdr.ubk.core.repository;

import kg.gov.mtsdr.ubk.common.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {

    Optional<FamilyMember> findByPin(String pin);
}
