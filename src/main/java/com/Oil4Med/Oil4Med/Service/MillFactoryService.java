package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Enum.AnalysisType;
import com.Oil4Med.Oil4Med.Model.Enum.Owner;

import java.util.Date;
import java.util.List;

public interface MillFactoryService {

    List<MillFactory> getMillFactories();
    MillFactory getMillFactoryById(Long id);
    MillFactory addMillFactory(MillFactory millFactory);
    void deleteMillFactory(MillFactory millFactory);
    void updateMillFactory(Long id, MillFactory millFactory);
    Extraction processingHarvest(OliveSupplyForExtraction oliveSupplyForExtraction, MillAgreement millAgreement,
                                 Machine machine, Date start_date, Date finishDate,
                                 double waterPer100kg, double averageMixingTime, double pressTemperature,
                                 boolean filtration);
    OilProductionBatch produceOil(Extraction extraction, AnalysisType analysisType, Boolean isForSale,
                                  double oilQuantity, Owner owner);
    PurchaseOil purchaseOilFromFarmer(Farmer farmer, OilProduct oilProduct, double weight,MillFactory millFactory, double price);
}

