package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.OilProduct;
import com.Oil4Med.Oil4Med.Repository.OilProductRepository;
import com.Oil4Med.Oil4Med.Service.OilProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OilProductImpl implements OilProductService {

    @Autowired
    OilProductRepository oilProductRepository;
    public OilProductImpl(OilProductRepository oilProductRepository){
        this.oilProductRepository=oilProductRepository;
    }
    @Override
    public List<OilProduct> getOilProduct() {
        List<OilProduct> oilProducts = new ArrayList<>();
        oilProductRepository.findAll().forEach(oilProducts::add);
        return oilProducts;
    }

    @Override
    public OilProduct getOilProductById(Long id) {
        return oilProductRepository.findById(id).get();
    }

    @Override
    public OilProduct addOilProduct(OilProduct oilProduct) {
        return oilProductRepository.save(oilProduct);
    }

    @Override
    public void deleteOilProduct(OilProduct oilProduct) {
        oilProductRepository.delete(oilProduct);
    }

    @Override
    public void updateOilProduct(Long id, OilProduct oilProduct) {
        OilProduct oilProductFromDB = oilProductRepository.findById(id).get();
        oilProductFromDB.setOilProductId(oilProductFromDB.getOilProductId());
        oilProductFromDB.setOilClass(oilProduct.getOilClass());
        oilProductFromDB.setAnalysisQuality1(oilProduct.getAnalysisQuality1());
        oilProductFromDB.setAnalysisQuality2(oilProduct.getAnalysisQuality2());
        oilProductFromDB.setAnalysisQuality3(oilProduct.getAnalysisQuality3());
        oilProductFromDB.setAnalysisQuality4(oilProduct.getAnalysisQuality4());
        oilProductFromDB.setAnalysisQuality5(oilProduct.getAnalysisQuality5());
        oilProductFromDB.setAnalysisQuality6(oilProduct.getAnalysisQuality6());
        oilProductFromDB.setOilQuantity(oilProduct.getOilQuantity());
        oilProductFromDB.setOilProductState(oilProduct.getOilProductState());
        oilProductFromDB.setStored(oilProduct.isStored());
        oilProductFromDB.setPacked(oilProduct.isPacked());
        oilProductFromDB.setForSale(oilProduct.isForSale());
        oilProductRepository.save(oilProductFromDB);
    }
}
