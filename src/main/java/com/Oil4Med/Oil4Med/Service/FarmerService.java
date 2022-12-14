package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.*;

import java.util.List;

public interface FarmerService {

    List<Farmer> getFarmers();
    Farmer getFarmerById(Long id);
    Farmer addFarmer(Farmer farmer);
    void deleteFarmer(Farmer farmer);
    void updateFarmer(Long farmerId, Farmer newFarmer);
    OliveSupplyForExtraction transportHarvestToMill(OliveHarvest oliveHarvest, double weight);
    void sellOil(OilProduct oilProduct, double oilQuantity, Consumer consumer);
    void setIntentionToSellOlive(OliveHarvest oliveHarvest);
    void setIntentionToSellOil(OilProduct oilProduct);

}
