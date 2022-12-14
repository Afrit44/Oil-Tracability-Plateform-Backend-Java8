package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.PurchaseHarvest;

import java.util.List;

public interface PurchaseHarvestService {
    List<PurchaseHarvest> getPurchaseHarvest();
    PurchaseHarvest getPurchaseHarvestById(Long id);
    PurchaseHarvest addPurchaseHarvest(PurchaseHarvest purchaseHarvest);
    void deletePurchaseHarvest(PurchaseHarvest purchaseHarvest);
    void updatePurchaseHarvest(Long id, PurchaseHarvest purchaseHarvest);
}
