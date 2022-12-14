package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.OilProductionBatch;
import com.Oil4Med.Oil4Med.Repository.OilProductionBatchRepository;
import com.Oil4Med.Oil4Med.Service.OilProductionBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OilProductionBatchImpl implements OilProductionBatchService {

    @Autowired
    OilProductionBatchRepository oilProductionBatchRepository;
    @Override
    public OilProductionBatch createOilProductionBatch(OilProductionBatch oilProductionBatch) {
        return oilProductionBatchRepository.save(oilProductionBatch);
    }

    @Override
    public OilProductionBatch updateOilProductionBatch(Long oilProductionBatchId,OilProductionBatch oilProductionBatch) {
        OilProductionBatch oilProductionBatchOld =oilProductionBatchRepository.findById(oilProductionBatchId).get();
        oilProductionBatchOld.setOilQuantity(oilProductionBatch.getOilQuantity());
        oilProductionBatchOld.setOwner(oilProductionBatch.getOwner());
        oilProductionBatchOld.setPurchaseDate(oilProductionBatch.getPurchaseDate());
        oilProductionBatchOld.setProductionForSale(oilProductionBatch.isProductionForSale());
        oilProductionBatchOld.setAnalysisType(oilProductionBatch.getAnalysisType());

        return oilProductionBatchRepository.save(oilProductionBatchOld);
    }

    @Override
    public void deleteOilProductionBatch(Long OilProductionBatchId) {
        oilProductionBatchRepository.deleteById(OilProductionBatchId);

    }

    @Override
    public List<OilProductionBatch> getOilProductionBatches() {
        return oilProductionBatchRepository.findAll();
    }

    @Override
    public OilProductionBatch getOilProductionBatchById(Long OilProductionBatchId) {
        return oilProductionBatchRepository.findById(OilProductionBatchId).orElse(null);
    }
}
