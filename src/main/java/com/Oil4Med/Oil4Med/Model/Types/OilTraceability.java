package com.Oil4Med.Oil4Med.Model.Types;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OilTraceability {

    Long oilProductId;

    Long packagingOperationId;

    Long storageAreaId;

    Long oilProductionBatchId;

    Long extractionId;

    Long millFactoryId;

    List<Long> machineIdList;

    List<Long> oliveSupplyForExtractionIdList;

    List<Long> oliveHarvestId;

    List<Long> oliveGroveId;

    List<Long> farmerId;

}