package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Types.OilTraceability;

import java.util.List;

public interface ConsumerService {
    List<Consumer> getConsumers();
    Consumer getConsumersById(Long id);
    Consumer addConsumer(Consumer consumer) throws Exception;
    void deleteConsumer(Consumer consumer);
    void updateConsumer(Long consumerId, Consumer newConsumer);
    PurchaseOil purchaseOilFromFarmer(Consumer consumer, Farmer farmer,OilProduct oilProduct, double quantity, double price);
    PurchaseOil purchaseOilFromMill(MillFactory millFactory, OilProduct oilProduct, double quantity, double price, Consumer consumer);
    OilTraceability checkTraceability(OilProduct oilProduct);


}
