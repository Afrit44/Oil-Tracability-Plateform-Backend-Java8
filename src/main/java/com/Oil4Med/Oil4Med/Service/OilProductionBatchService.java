package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.OilProductionBatch;

import java.util.List;

public interface OilProductionBatchService {
    OilProductionBatch createOilProductionBatch(OilProductionBatch oilProductionBatch);
    OilProductionBatch updateOilProductionBatch(Long oilProductionBatchId, OilProductionBatch oilProductionBatch);
    void deleteOilProductionBatch(Long OilProductionBatchId);
    List<OilProductionBatch> getOilProductionBatches();
    OilProductionBatch getOilProductionBatchById(Long OilProductionBatchId);
}
