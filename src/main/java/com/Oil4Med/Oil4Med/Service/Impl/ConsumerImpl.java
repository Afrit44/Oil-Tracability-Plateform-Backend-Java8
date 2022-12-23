package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Enum.Buyer;
import com.Oil4Med.Oil4Med.Model.Enum.Seller;
import com.Oil4Med.Oil4Med.Model.Types.OilTraceability;
import com.Oil4Med.Oil4Med.Repository.AdminRepository;
import com.Oil4Med.Oil4Med.Repository.ConsumerRepository;
import com.Oil4Med.Oil4Med.Service.ConsumerService;
import com.Oil4Med.Oil4Med.Service.OilProductService;
import com.Oil4Med.Oil4Med.Service.PurchaseOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerImpl implements ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;
    @Autowired
    PurchaseOilService purchaseOilService;
    @Autowired
    OilProductService oilProductService;
    @Autowired
    AdminRepository adminRepository;
    @Override
    public List<Consumer> getConsumers() {
        List<Consumer> consumers = new ArrayList<>();
        consumerRepository.findAll().forEach(consumers::add);
        return consumers;
    }

    @Override
    public Consumer getConsumersById(Long id) {
        return consumerRepository.findById(id).get();
    }

    @Override
    public Consumer addConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    @Override
    public void deleteConsumer(Consumer consumer) {

        deleteConsumerAdminRelation(consumer);
        consumerRepository.delete(consumer);
    }

    //We have to delete the foreign key in admin_Farmers table in order to fully delete the Farmer user otherwise
    //it raise ERROR : update or delete on table "farmer" violates foreign key constraint on table "admin_farmers"
    private void deleteConsumerAdminRelation(Consumer consumer) {
        Admin admin = consumer.getAdminCreatedConsumer();
        consumer.setAdminCreatedConsumer(null);
        List<Consumer> listOfConsumersByAdmin = admin.getConsumers();
        listOfConsumersByAdmin.remove(consumer);
        admin.setConsumers(listOfConsumersByAdmin);
        adminRepository.save(admin);
        consumerRepository.save(consumer);
    }
    @Override
    public void updateConsumer(Long consumerId, Consumer newConsumer) {
        Consumer consumer = consumerRepository.findById(consumerId).get();
        consumer.setEmail(newConsumer.getEmail());
        consumer.setLastName(newConsumer.getLastName());
        consumer.setFirstName(newConsumer.getFirstName());
        consumer.setPassword(newConsumer.getPassword());
        consumerRepository.save(consumer);
    }

    @Override
    public PurchaseOil purchaseOilFromFarmer(Consumer consumer, Farmer farmer, OilProduct oilProduct, double quantity, double price) {
        //Subtract the quantity bought by the consumer from the farmer and update it in database
        oilProduct.setOilQuantity(oilProduct.getOilQuantity()-quantity);
        oilProductService.updateOilProduct(oilProduct.getOilProductId(),oilProduct);
        //Add a new Purchase Oil
        PurchaseOil purchaseOil = new PurchaseOil();
        purchaseOil.setBuyerId(consumer.getConsumerId());
        purchaseOil.setBuyerType(Buyer.CONSUMER);
        purchaseOil.setSellerId(farmer.getFarmerId());
        purchaseOil.setSellerType(Seller.FARMER);
        purchaseOil.setPrice(price);
        purchaseOil.setQuantity(quantity);
        purchaseOil.setConsumer(consumer);
        purchaseOil.setOilProduct(oilProduct);
        return purchaseOilService.addPurchaseOil(purchaseOil);
    }

    @Override
    public PurchaseOil purchaseOilFromMill(MillFactory millFactory, OilProduct oilProduct, double quantity, double price, Consumer consumer) {
        //Subtract the quantity bought by the consumer from the farmer and update it in database
        if (oilProduct.getOilQuantity()-quantity<0){
            //Quantity subtracted is more than the one already in hand.
            throw new RuntimeException();
        }
        oilProduct.setOilQuantity(oilProduct.getOilQuantity()-quantity);
        oilProductService.updateOilProduct(oilProduct.getOilProductId(),oilProduct);
        //Add a new Purchase Oil
        PurchaseOil purchaseOil = new PurchaseOil();
        purchaseOil.setBuyerId(consumer.getConsumerId());
        purchaseOil.setBuyerType(Buyer.CONSUMER);
        purchaseOil.setSellerId(millFactory.getMillId());
        purchaseOil.setSellerType(Seller.MILL);
        purchaseOil.setPrice(price);
        purchaseOil.setQuantity(quantity);
        purchaseOil.setConsumer(consumer);
        purchaseOil.setOilProduct(oilProduct);
        return purchaseOilService.addPurchaseOil(purchaseOil);
    }

    @Override
    public OilTraceability checkTraceability(OilProduct oilProduct) {

        OilTraceability oilTraceability = new OilTraceability();

        //Setting oilProductId
        oilTraceability.setOilProductId(oilProduct.getOilProductId());

        //Checking if the Oil Has been packaged, If yes then we Set the packagingOperationId
        if (oilProduct.isPacked()){
            oilTraceability.setPackagingOperationId(oilProduct.getPackagingOperation().getPackagingId());
        }else{
            oilTraceability.setStorageAreaId(null);
        }

        //Checking if the Oil Has been stocked, If yes then we Set the StorageAreaId
        if (oilProduct.isStored()){
            oilTraceability.setStorageAreaId(oilProduct.getStorageArea().getStorageAreaId());
        }else{
            oilTraceability.setStorageAreaId(null);
        }

        //Setting oilProductionBatchId
        OilProductionBatch oilProductionBatch = oilProduct.getOilProductionBatch();
        oilTraceability.setOilProductionBatchId(oilProductionBatch.getProductionBatchId());

        //Setting extractionId
        Extraction extraction = oilProductionBatch.getExtraction();
        oilTraceability.setExtractionId(extraction.getExtractionId());

        //Setting Machine List via link with extraction
        List<Machine> machineList = extraction.getMachinesList();
        List<Long> machineListId = new ArrayList<>();
        for (Machine machine : machineList){
            machineListId.add(machine.getMachineId());
        }
        oilTraceability.setMachineIdList(machineListId);

        //Setting MillFactory
        MillFactory millFactory = machineList.get(0).getMillFactory();
        oilTraceability.setMillFactoryId(millFactory.getMillId());


        //Setting OliveSupply
        List<OliveSupplyForExtraction> oliveSupplyForExtractionList = extraction.getOliveSupplyForExtractionList();
        List<Long> oSupplyFEIdList = new ArrayList<>();
        for(OliveSupplyForExtraction oliveSupplyForExtraction : oliveSupplyForExtractionList){
            oSupplyFEIdList.add(oliveSupplyForExtraction.getSupplyId());
        }
        oilTraceability.setOliveSupplyForExtractionIdList(oSupplyFEIdList);

        //Setting oliveHarvest
        List<OliveHarvest> oliveHarvestList = new ArrayList<>();
        List<Long> oliveHarvestIdList = new ArrayList<>();
        for (OliveSupplyForExtraction oliveSupplyForExtraction : oliveSupplyForExtractionList){
            oliveHarvestList.add(oliveSupplyForExtraction.getOliveHarvest());
            oliveHarvestIdList.add(oliveSupplyForExtraction.getOliveHarvest().getHarvestId());
        }
        oilTraceability.setOliveHarvestId(oliveHarvestIdList);

        //Setting oliveGrove
        List<OliveGrove> oliveGroveList = new ArrayList<>();
        List<Long> oliveGroveIdList = new ArrayList<>();
        for (OliveHarvest oliveHarvest : oliveHarvestList){
            oliveGroveList.add(oliveHarvest.getOliveGrove());
            oliveGroveIdList.add(oliveHarvest.getOliveGrove().getGroveId());
        }
        oilTraceability.setOliveHarvestId(oliveGroveIdList);

        //Setting Farmer
        List<Long> farmerIdList = new ArrayList<>();
        for (OliveGrove oliveGrove : oliveGroveList){
            oliveHarvestIdList.add(oliveGrove.getFarmer().getFarmerId());
        }
        oilTraceability.setOliveHarvestId(farmerIdList);
        oilTraceability.getFarmerId().get(0);

        //We don't have to save the entity into the database
        return oilTraceability;
    }


}
