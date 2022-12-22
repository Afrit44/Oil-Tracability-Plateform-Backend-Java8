package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Enum.*;
import com.Oil4Med.Oil4Med.Repository.*;
import com.Oil4Med.Oil4Med.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class MillFactoryImpl implements MillFactoryService {
    @Autowired
    MillFactoryRepository millFactoryRepository;
    @Autowired
    ExtractionService extractionService;
    @Autowired
    OilProductionBatchService oilProductionBatchService;
    @Autowired
    PurchaseOilService purchaseOilService;
    @Autowired
    OliveSupplyForExtractionRepository oliveSupplyForExtractionRepository;
    @Autowired
    OilProductService oilProductService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MillManagerRepository millManagerRepository;
    @Autowired
    ExtractionRepository extractionRepository;

    @Override
    public List<MillFactory> getMillFactories() {
        List<MillFactory> millFactories  = new ArrayList<>();
        millFactoryRepository.findAll().forEach(millFactories::add);
        return millFactories;
    }

    @Override
    public MillFactory getMillFactoryById(Long id) {
        return millFactoryRepository.findById(id).get();
    }

    @Override
    public MillFactory addMillFactory(MillFactory millFactory) {
        return millFactoryRepository.save(millFactory);
    }

    @Override
    public void deleteMillFactory(MillFactory millFactory) {

        deleteMillFactoryAdminRelation(millFactory);
        millFactoryRepository.delete(millFactory);
    }

    //We have to delete the foreign key in admin_Farmers table in order to fully delete the Farmer user otherwise
    //it raise ERROR : update or delete on table "farmer" violates foreign key constraint on table "admin_farmers"
    private void deleteMillFactoryAdminRelation(MillFactory millFactory) {
        Admin admin = millFactory.getAdmin();
        MillManager millManager = millFactory.getMillManager();
        millFactory.setMillManager(null);
        millFactory.setAdmin(null);
        admin.getMills().remove(millFactory);
        admin.setMills(admin.getMills());
        millManager.setMillFactory(null);
        millManagerRepository.save(millManager);
        adminRepository.save(admin);
        millFactoryRepository.save(millFactory);
    }
    @Override
    public void updateMillFactory(Long id, MillFactory millFactory) {
        MillFactory millFactoryFromDb = millFactoryRepository.findById(id).get();
        millFactoryFromDb.setMillId(millFactory.getMillId());
        millFactoryFromDb.setMillName(millFactory.getMillName());
        millFactoryFromDb.setPassword(millFactory.getPassword());
        millFactoryFromDb.setMillingCapacity(millFactory.getMillingCapacity());
        millFactoryFromDb.setAddress(millFactory.getAddress());
        millFactoryFromDb.setEmail(millFactory.getEmail());
        millFactoryFromDb.setFax(millFactory.getFax());
        millFactoryFromDb.setNumberOfMachines(millFactory.getNumberOfMachines());
        millFactoryFromDb.setManagerName(millFactory.getManagerName());
        millFactoryFromDb.setStoringMean(millFactory.getStoringMean());
        millFactoryFromDb.setYearOfCreation(millFactory.getYearOfCreation());
        millFactoryFromDb.setPhoneNumber(millFactory.getPhoneNumber());
        millFactoryFromDb.setWebsite(millFactory.getWebsite());
        millFactoryRepository.save(millFactoryFromDb);
    }

    @Override
    public Extraction processingHarvest(OliveSupplyForExtraction oliveSupplyForExtraction, MillAgreement millAgreement,
                                        ExtractionStatus extractionStatus,Machine machine,
                                        Date start_date, Date finishDate, double waterPer100kg,
                                        double averageMixingTime, double pressTemperature,
                                        boolean filtration) {

        //Creation of the new extraction
        Extraction extraction = new Extraction();
        extraction.setExtractionStatus(extractionStatus);
        extraction.setStartDate(start_date);
        extraction.setFinishDate(finishDate);
        extraction.setWaterPer100Kg(waterPer100kg);
        extraction.setAverageMixingTime(averageMixingTime);
        extraction.setPressTemperature(pressTemperature);
        extraction.setFiltration(filtration);

        //I'll comment it later since maandich wsaa el bel tawa. TODO
        List<OliveSupplyForExtraction> listOSFE;
        if(extraction.getOliveSupplyForExtractionList()==null){
            listOSFE = new ArrayList<>();
        }else{
            listOSFE = extraction.getOliveSupplyForExtractionList();
        }
        listOSFE.add(oliveSupplyForExtraction);
        extraction.setOliveSupplyForExtractionList(listOSFE);

        //I'll comment it later since maandich wsaa el bel tawa. 2.0 TODO
        List<Machine> listMachine;
        if (extraction.getMachinesList()==null){
            listMachine = new ArrayList<>();
        }else{
            listMachine = extraction.getMachinesList();
        }
        listMachine.add(machine);
        extraction.setMachinesList(listMachine);

//        extraction.getMillAgreementList().add(millAgreement);
        extraction=extractionService.addExtraction(extraction);
        extractionRepository.save(extraction);
        //Link extraction with the initial oliveSupply
        oliveSupplyForExtraction.getExtractionList().add(extraction);
        oliveSupplyForExtractionRepository.save(oliveSupplyForExtraction);
        return extraction;
    }

    @Override
    public OilProductionBatch produceOil(Extraction extraction, AnalysisType analysisType, Boolean isForSale,
                                         double oilQuantity, Owner owner) {

        //Creation of the new oilProductionBatch
        OilProductionBatch oilProductionBatch = new OilProductionBatch();
//        oilProductionBatch.setExtractionId(extraction.getExtractionId());
        oilProductionBatch.setAnalysisType(analysisType);
        oilProductionBatch.setProductionForSale(isForSale);
        oilProductionBatch.setOilQuantity(oilQuantity);
        oilProductionBatch.setOwner(owner);
        oilProductionBatch.setExtraction(extraction);
        oilProductionBatch=oilProductionBatchService.createOilProductionBatch(oilProductionBatch);
        //Linking the OilProductionBatch with the extraction
        extraction.setOilProductionBatch(oilProductionBatch);
        extractionService.updateExtraction(extraction.getExtractionId(),extraction);
        return oilProductionBatch;
    }

    @Override
    public PurchaseOil purchaseOilFromFarmer(Farmer farmer, OilProduct oilProduct, double weight,
                                             MillFactory millFactory, double price) {
        //Subtract the quantity bought by the consumer from the farmer and update it in database
        oilProduct.setOilQuantity(oilProduct.getOilQuantity()-weight);
        oilProductService.updateOilProduct(oilProduct.getOilProductId(),oilProduct);
        //Add a new Purchase Oil
        PurchaseOil purchaseOil = new PurchaseOil();
        purchaseOil.setBuyerId(millFactory.getMillId());
        purchaseOil.setBuyerType(Buyer.MILL);
        purchaseOil.setSellerId(farmer.getFarmerId());
        purchaseOil.setSellerType(Seller.FARMER);
        purchaseOil.setPrice(price);
        purchaseOil.setQuantity(weight);
        purchaseOil.setOilProduct(oilProduct);
        return purchaseOilService.addPurchaseOil(purchaseOil);

    }
}
