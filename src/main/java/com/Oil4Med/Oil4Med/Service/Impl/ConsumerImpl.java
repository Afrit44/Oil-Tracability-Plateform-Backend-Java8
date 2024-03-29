package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.DTO.AdminDTO;
import com.Oil4Med.Oil4Med.DTO.ConsumerDTO;
import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Enum.Buyer;
import com.Oil4Med.Oil4Med.Model.Enum.Seller;
import com.Oil4Med.Oil4Med.Model.Types.OilTraceability;
import com.Oil4Med.Oil4Med.Repository.AdminRepository;
import com.Oil4Med.Oil4Med.Repository.ConsumerRepository;
import com.Oil4Med.Oil4Med.Repository.OliveSupplyForExtractionRepository;
import com.Oil4Med.Oil4Med.Service.ConsumerService;
import com.Oil4Med.Oil4Med.Service.OilProductService;
import com.Oil4Med.Oil4Med.Service.PurchaseOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminImpl adminImpl;
    @Autowired
    FarmerImpl farmerImpl;
    @Autowired
    MillManagerImpl millManagerImpl;

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
    public Consumer addConsumer(Consumer consumer) throws Exception {
        if (consumer == null) {
            throw new EntityNotFoundException("ID_IS_NULL");
        }
        //Check email is unique
        if(checkEmailExistInConsumer(consumer.getEmail()) ||
            adminImpl.checkEmailExistAsAdminEmail(consumer.getEmail()) ||
                farmerImpl.checkEmailExistInFarmer(consumer.getEmail()) ||
                    millManagerImpl.checkEmailExitMillManager(consumer.getEmail())
        ){
            throw new Exception("Email already exists in DataBase");
        }
        consumer.setPassword(passwordEncoder.encode(consumer.getPassword()));
        return consumerRepository.save(consumer);
    }
    boolean checkEmailExistInConsumer(String email){
        List<String> listEmails = new ArrayList<>();
        List<ConsumerDTO> consumerDTOS = consumerRepository.findAll().stream()
                .map(ConsumerDTO::fromEntity).collect(Collectors.toList());
        for (ConsumerDTO consumerDTO : consumerDTOS){
            listEmails.add(consumerDTO.getEmail());
        }
        if(listEmails.contains(email)){
            return true;
        }
        return false;
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
    public PurchaseOil purchaseOilFromFarmer(Consumer consumer, Farmer farmer, OilProduct oilProduct,
                                             double quantity, double price) {
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
    public PurchaseOil purchaseOilFromMill(MillFactory millFactory, OilProduct oilProduct, double quantity,
                                           double price, Consumer consumer) {
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

        setTraceabilityOilProduct( oilProduct,  oilTraceability);

        checkSetPackaging(oilProduct,oilTraceability);

        checkSetStocked(oilProduct, oilTraceability);

        OilProductionBatch oilProductionBatch = setOilProductionBatchTraceability(oilProduct,oilTraceability);

        Extraction extraction = setExtractionTraceability( oilProductionBatch,oilTraceability);

        List<Machine> machineList = setMachineListTraceability(extraction,oilTraceability);

        MillFactory millFactory= setMillFactoryTraceability(machineList,oilTraceability);

        List<OliveSupplyForExtraction> oliveSupplyForExtractionList=setOliveSupplyTraceability(extraction,oilTraceability);

        List<OliveHarvest> oliveHarvestList = setOliveHarvestTraceability(oliveSupplyForExtractionList, oilTraceability);

        List<OliveGrove> oliveGroveList = setOliveGroveTraceability(oliveHarvestList, oilTraceability);

        setFarmerTraceability(oliveGroveList,oilTraceability);

        //We don't have to save the entity into the database
        return oilTraceability;
    }

    //**THESE FUNCTIONS ARE MADE FOR THE CHECK TRACEABILITY FUNCTION ABOVE. NOT DECLARED IN THE SERVICE INTERFACE**
    //**AFRIT IS KILLING IT OVER HERE**

    //Setting oilProductId
    void setTraceabilityOilProduct(OilProduct oilProduct, OilTraceability oilTraceability){
        oilTraceability.setOilProductId(oilProduct.getOilProductId());
    }
    //Checking if the Oil Has been packaged, If yes then we Set the packagingOperationId
    void checkSetPackaging (OilProduct oilProduct, OilTraceability oilTraceability){
        if (oilProduct.isPacked()) {
            oilTraceability.setPackagingOperationId(oilProduct.getPackagingOperation().getPackagingId());
        } else {
            oilTraceability.setStorageAreaId(null);
        }
    }
    //Checking if the Oil Has been stocked, If yes then we Set the StorageAreaId
    void checkSetStocked (OilProduct oilProduct, OilTraceability oilTraceability) {
        if (oilProduct.isStored()) {
            oilTraceability.setStorageAreaId(oilProduct.getStorageArea().getStorageAreaId());
        } else {
            oilTraceability.setStorageAreaId(null);
        }
    }
    //Setting oilProductionBatchId
    OilProductionBatch setOilProductionBatchTraceability(OilProduct oilProduct,OilTraceability oilTraceability){
        OilProductionBatch oilProductionBatch = oilProduct.getOilProductionBatch();
        oilTraceability.setOilProductionBatchId(oilProductionBatch.getProductionBatchId());
        return oilProductionBatch;
    }
    //Setting extractionId
    Extraction setExtractionTraceability(OilProductionBatch oilProductionBatch, OilTraceability oilTraceability){
        Extraction extraction = oilProductionBatch.getExtraction();
        oilTraceability.setExtractionId(extraction.getExtractionId());
        return extraction;
    }
    //Setting Machine List via link with extraction
    List<Machine> setMachineListTraceability(Extraction extraction, OilTraceability oilTraceability){
        List<Machine> machineList = extraction.getMachinesList();
        List<Long> machineListId = new ArrayList<>();
        for (Machine machine : machineList) {
            machineListId.add(machine.getMachineId());
        }
        oilTraceability.setMachineIdList(machineListId);
        return machineList;
    }
    //Setting MillFactory
    MillFactory setMillFactoryTraceability(List<Machine> machineList, OilTraceability oilTraceability){
        MillFactory millFactory = machineList.get(0).getMillFactory();
        oilTraceability.setMillFactoryId(millFactory.getMillId());
        return millFactory;
    }

    //Setting OliveSupply
    List<OliveSupplyForExtraction> setOliveSupplyTraceability(Extraction extraction, OilTraceability oilTraceability) {
        List<OliveSupplyForExtraction> oliveSupplyForExtractionList = extraction.getOliveSupplyForExtractionList();
        List<Long> oSupplyFEIdList = new ArrayList<>();
        for (OliveSupplyForExtraction oliveSupplyForExtraction : oliveSupplyForExtractionList) {
            oSupplyFEIdList.add(oliveSupplyForExtraction.getSupplyId());
        }
        oilTraceability.setOliveSupplyForExtractionIdList(oSupplyFEIdList);
        return oliveSupplyForExtractionList;
    }
    //Setting oliveHarvest
    List<OliveHarvest> setOliveHarvestTraceability(List<OliveSupplyForExtraction> oliveSupplyForExtractionList,
                                                   OilTraceability oilTraceability){

        List<OliveHarvest> oliveHarvestList = new ArrayList<>();
        List<Long> oliveHarvestIdList = new ArrayList<>();
        for (OliveSupplyForExtraction oliveSupplyForExtraction : oliveSupplyForExtractionList){
            oliveHarvestList.add(oliveSupplyForExtraction.getOliveHarvest());
            oliveHarvestIdList.add(oliveSupplyForExtraction.getOliveHarvest().getHarvestId());
        }
        oilTraceability.setOliveHarvestId(oliveHarvestIdList);
        return oliveHarvestList;
    }
    //Setting oliveGrove
    List<OliveGrove> setOliveGroveTraceability(List<OliveHarvest> oliveHarvestList, OilTraceability oilTraceability){
        List<OliveGrove> oliveGroveList = new ArrayList<>();
        List<Long> oliveGroveIdList = new ArrayList<>();
        for (OliveHarvest oliveHarvest : oliveHarvestList) {
            oliveGroveList.add(oliveHarvest.getOliveGrove());
            oliveGroveIdList.add(oliveHarvest.getOliveGrove().getGroveId());
        }
        oilTraceability.setOliveGroveId(oliveGroveIdList);
        return oliveGroveList;
    }
    //Setting Farmer
    void setFarmerTraceability(List<OliveGrove> oliveGroveList,OilTraceability oilTraceability) {
        List<Long> farmerIdList = new ArrayList<>();
        for (OliveGrove oliveGrove : oliveGroveList) {
            farmerIdList.add(oliveGrove.getFarmer().getFarmerId());
        }
        oilTraceability.setFarmerId(farmerIdList);
    }
}
