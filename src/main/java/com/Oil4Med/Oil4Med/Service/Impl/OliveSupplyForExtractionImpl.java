package com.Oil4Med.Oil4Med.Service.Impl;


import com.Oil4Med.Oil4Med.Model.OliveHarvest;
import com.Oil4Med.Oil4Med.Model.OliveSupplyForExtraction;
import com.Oil4Med.Oil4Med.Repository.OliveHarvestRepository;
import com.Oil4Med.Oil4Med.Repository.OliveSupplyForExtractionRepository;
import com.Oil4Med.Oil4Med.Service.OliveSupplyForExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OliveSupplyForExtractionImpl implements OliveSupplyForExtractionService {
    @Autowired
    OliveHarvestRepository oliveHarvestRepository;
    @Autowired
    OliveSupplyForExtractionRepository oliveSupplyForExtractionRepository;
    public OliveSupplyForExtractionImpl(OliveHarvestRepository oliveHarvestRepository, OliveSupplyForExtractionRepository oliveSupplyForExtractionRepository)
    {
        this.oliveSupplyForExtractionRepository =oliveSupplyForExtractionRepository;
        this.oliveHarvestRepository=oliveHarvestRepository;

    }
    @Override
    public OliveSupplyForExtraction createOSupplyForExtraction(OliveSupplyForExtraction oSupplyForExtraction) {
        return (oliveSupplyForExtractionRepository.save(oSupplyForExtraction));
    }

    @Override
    public OliveSupplyForExtraction updateOSupplyForExtraction(Long oSupplyForExtractId, Long harvestId, OliveSupplyForExtraction oSupplyForExtraction) {
        OliveSupplyForExtraction oldSupplyExtraction = oliveSupplyForExtractionRepository.findById(oSupplyForExtractId).get();
        OliveHarvest oldHarvest = oliveHarvestRepository.findById(harvestId).get();
        oldSupplyExtraction.setOliveHarvest(oldHarvest);
        oldSupplyExtraction.setSupplyId(oSupplyForExtraction.getSupplyId());
        oldSupplyExtraction.setExtractionType(oldSupplyExtraction.getExtractionType());
        oldSupplyExtraction.setWeight(oldSupplyExtraction.getWeight());

        return oliveSupplyForExtractionRepository.save(oldSupplyExtraction);
    }

    @Override
    public void deleteOSupplyForExtraction(Long oSupplyForExtractId) {
        oliveSupplyForExtractionRepository.deleteById(oSupplyForExtractId);

    }

    @Override
    public List<OliveSupplyForExtraction> geToSupplyForExtracts() {
        return oliveSupplyForExtractionRepository.findAll();
    }

    @Override
    public OliveSupplyForExtraction getOSupplyForExtractById(Long oSupplyForExtractId) {
        return oliveSupplyForExtractionRepository.findById(oSupplyForExtractId).orElse(null);
    }
}
