package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.OliveSupplyForExtraction;

import java.util.List;

public interface OliveSupplyForExtractionService {
    OliveSupplyForExtraction createOSupplyForExtraction(OliveSupplyForExtraction oSupplyForExtraction);
    OliveSupplyForExtraction updateOSupplyForExtraction(Long oSupplyForExtractId, Long harvestId, OliveSupplyForExtraction oSupplyForExtraction);
    void deleteOSupplyForExtraction(Long oSupplyForExtractId);
    List<com.Oil4Med.Oil4Med.Model.OliveSupplyForExtraction> geToSupplyForExtracts();
    OliveSupplyForExtraction getOSupplyForExtractById(Long oSupplyForExtractId);
}
