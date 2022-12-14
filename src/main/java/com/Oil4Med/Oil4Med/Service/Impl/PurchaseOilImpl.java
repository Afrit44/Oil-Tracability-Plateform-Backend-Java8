package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.PurchaseOil;
import com.Oil4Med.Oil4Med.Repository.PurchaseOilRepository;
import com.Oil4Med.Oil4Med.Service.PurchaseOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOilImpl implements PurchaseOilService {

    @Autowired
    PurchaseOilRepository purchaseOilRepository;
    @Override
    public List<PurchaseOil> getPurchaseOil() {
        List<PurchaseOil> purchaseOilList = new ArrayList<>();
        purchaseOilRepository.findAll().forEach(purchaseOilList::add);
        return purchaseOilList;
    }

    @Override
    public PurchaseOil getPurchaseOilById(Long id) {
        return purchaseOilRepository.findById(id).get();
    }

    @Override
    public PurchaseOil addPurchaseOil(PurchaseOil purchaseOil) {
        return purchaseOilRepository.save(purchaseOil);
    }

    @Override
    public void deletePurchaseOil(PurchaseOil purchaseOil) {
        purchaseOilRepository.delete(purchaseOil);
    }

    @Override
    public void updatePurchaseOil(Long id, PurchaseOil purchaseOil) {
        PurchaseOil purchaseOilFromDB = purchaseOilRepository.findById(id).get();
        purchaseOilFromDB.setPurchaseId(purchaseOil.getPurchaseId());
        purchaseOilFromDB.setSellerId(purchaseOil.getSellerId());
        purchaseOilFromDB.setSellerType(purchaseOil.getSellerType());
        purchaseOilFromDB.setBuyerId(purchaseOil.getBuyerId());
        purchaseOilFromDB.setBuyerType(purchaseOil.getBuyerType());
        purchaseOilFromDB.setQuantity(purchaseOil.getQuantity());
        purchaseOilFromDB.setPrice(purchaseOil.getPrice());
        purchaseOilRepository.save(purchaseOilFromDB);
    }
}
