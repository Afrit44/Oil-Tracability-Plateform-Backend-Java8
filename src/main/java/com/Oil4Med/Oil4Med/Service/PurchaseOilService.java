package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.PurchaseOil;

import java.util.List;

public interface PurchaseOilService {
    List<PurchaseOil> getPurchaseOil();
    PurchaseOil getPurchaseOilById(Long id);
    PurchaseOil addPurchaseOil(PurchaseOil purchaseOil);
    void deletePurchaseOil(PurchaseOil purchaseOil);
    void updatePurchaseOil(Long id, PurchaseOil purchaseOil);
}
