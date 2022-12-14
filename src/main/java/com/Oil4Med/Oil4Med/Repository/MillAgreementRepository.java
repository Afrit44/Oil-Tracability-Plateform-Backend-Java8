package com.Oil4Med.Oil4Med.Repository;

import com.Oil4Med.Oil4Med.Model.MillAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MillAgreementRepository extends JpaRepository<MillAgreement, Long> {
}
