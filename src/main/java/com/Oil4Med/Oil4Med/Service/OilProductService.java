package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.OilProduct;

import java.util.List;

public interface OilProductService {
    List<OilProduct> getOilProduct();
    OilProduct getOilProductById(Long id);
    OilProduct addOilProduct(OilProduct oilProduct);
    void deleteOilProduct(OilProduct oilProduct);
    void updateOilProduct(Long id, OilProduct oilProduct);
}
