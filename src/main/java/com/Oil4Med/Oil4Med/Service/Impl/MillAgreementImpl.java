package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.MillAgreement;
import com.Oil4Med.Oil4Med.Repository.MillAgreementRepository;
import com.Oil4Med.Oil4Med.Service.MillAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MillAgreementImpl implements MillAgreementService {

    @Autowired
    MillAgreementRepository millAgreementRepository;

    @Override
    public MillAgreement addMillAgreement(MillAgreement millAgreement) {

        return millAgreementRepository.save(millAgreement);
    }
    @Override
    public List<MillAgreement> getMillAgreements() {
        List<MillAgreement> millAgreements = new ArrayList<>();
        millAgreementRepository.findAll().forEach(millAgreements::add);
        return millAgreements;
    }
    @Override
    public MillAgreement getMillAgreementById(Long id) {

        return millAgreementRepository.findById(id).get();
    }
    @Override
    public void deleteMillAgreement(MillAgreement millAgreement) {
        millAgreementRepository.delete(millAgreement);
    }
    @Override
    public void updateMillAgreement(Long id, MillAgreement millAgreement) {
        MillAgreement millAgreementFromDb = millAgreementRepository.findById(id).get();
        millAgreementFromDb.setMillAgreementId(millAgreement.getMillAgreementId());
        millAgreementFromDb.setHarvestId(millAgreement.getHarvestId());
//        millAgreementFromDb.setExtraction(millAgreement.getExtraction());
        millAgreementFromDb.setOliveQuantity(millAgreement.getOliveQuantity());
        millAgreementFromDb.setOliveQuantityToMill(millAgreement.getOliveQuantityToMill());
        millAgreementFromDb.setMillOlive(millAgreement.isMillOlive());
        millAgreementFromDb.setSellOlive(millAgreement.isSellOlive());
        millAgreementFromDb.setProcessingDate(millAgreement.getProcessingDate());
//        millAgreementFromDb.setStorageArea(millAgreement.getStorageArea());
        millAgreementRepository.save(millAgreementFromDb);
    }
}
