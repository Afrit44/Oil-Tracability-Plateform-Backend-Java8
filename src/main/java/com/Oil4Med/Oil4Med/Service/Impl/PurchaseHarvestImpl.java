package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.PurchaseHarvest;
import com.Oil4Med.Oil4Med.Repository.PurchaseHarvestRepository;
import com.Oil4Med.Oil4Med.Service.PurchaseHarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseHarvestImpl implements PurchaseHarvestService {

    @Autowired
    PurchaseHarvestRepository purchaseHarvestRepository;
    @Override
    public List<PurchaseHarvest> getPurchaseHarvest() {
        List<PurchaseHarvest> purchaseHarvestList = new ArrayList<>();
        purchaseHarvestRepository.findAll().forEach(purchaseHarvestList::add);
        return purchaseHarvestList;
    }

    @Override
    public PurchaseHarvest getPurchaseHarvestById(Long id) {
        return purchaseHarvestRepository.findById(id).get();
    }

    @Override
    public PurchaseHarvest addPurchaseHarvest(PurchaseHarvest purchaseHarvest) {
        return purchaseHarvestRepository.save(purchaseHarvest);
    }

    @Override
    public void deletePurchaseHarvest(PurchaseHarvest purchaseHarvest) {
        purchaseHarvestRepository.delete(purchaseHarvest);
    }

    @Override
    public void updatePurchaseHarvest(Long id, PurchaseHarvest purchaseHarvest) {
        PurchaseHarvest purchaseHarvestFromDB = purchaseHarvestRepository.findById(id).get();
        purchaseHarvestFromDB.setPurchaseHId(purchaseHarvest.getPurchaseHId());
        purchaseHarvestFromDB.setBuyerId(purchaseHarvest.getBuyerId());
        purchaseHarvestFromDB.setQuantity(purchaseHarvest.getQuantity());
        purchaseHarvestRepository.save(purchaseHarvestFromDB);
    }
}
