package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.MillAgreement;

import java.util.List;

public interface MillAgreementService {

    List<MillAgreement> getMillAgreements();
    MillAgreement getMillAgreementById(Long id);
    MillAgreement addMillAgreement(MillAgreement millAgreement);
    void deleteMillAgreement(MillAgreement millAgreement);
    void updateMillAgreement(Long id, MillAgreement millAgreement);
}
