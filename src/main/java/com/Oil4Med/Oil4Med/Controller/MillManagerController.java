package com.Oil4Med.Oil4Med.Controller;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Enum.AnalysisType;
import com.Oil4Med.Oil4Med.Model.Enum.Owner;
import com.Oil4Med.Oil4Med.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/oil4med/MillManager/")
public class MillManagerController {
    @Autowired
    MillManagerService millManagerService ;
    @Autowired
    MillFactoryService millFactoryService;
    @Autowired
    MachineService machineService;
    @Autowired
    ExtractionService extractionService;
    @Autowired
    OilProductionBatchService oilProductionBatchService;
    //Crud MillManager
    @PostMapping("/createMillManager")
    public MillManager createMillManager(@RequestBody MillManager millManager) throws Exception {
        return millManagerService.addMillManager(millManager);
    }
    @GetMapping("/getMillManagerById")
    public MillManager getMillManagerById(@RequestParam Long managerId) {
        return millManagerService.getMillManagerById(managerId);
    }
    @GetMapping("/getAllMillManagers")
    public List<MillManager> getAllMillManagers() {
        List<MillManager> millManagers = millManagerService.getMillManagers();
        return millManagers;
    }
    @PutMapping("/updateMillManager")
    public void updateMillManager(@RequestParam  Long managerId,MillManager millManager){
        millManagerService.updateMillManager(managerId,millManager);
    }
    @DeleteMapping("/deleteMillManager")
    public void deleteMillManager(long managerId){

        millManagerService.deleteMillManager(millManagerService.getMillManagerById(managerId));
    }

    //Crud MillFactory
    @PostMapping("/createMillFactory")
    public MillFactory createMillFactory(@RequestBody MillFactory millFactory) throws Exception {
        return millFactoryService.addMillFactory(millFactory);
    }
    @GetMapping("/getMillFactoryById")
    public MillFactory getMillFactoryById(@RequestParam Long factoryId) {
        return millFactoryService.getMillFactoryById(factoryId);
    }
    @GetMapping("/getAllMillFactories")
    public List<MillFactory> getAllMillFactories() {
        List<MillFactory> millFactories = millFactoryService.getMillFactories();
        return millFactories;
    }
    @PutMapping("/updateMillFactory")
    public void updateMillFactory(@RequestParam  Long factoryId,MillFactory millFactory){
        millFactoryService.updateMillFactory(factoryId,millFactory);
    }
    @DeleteMapping("/deleteMillFactory")
    public void deleteMillFactory(long factoryId){
        millFactoryService.deleteMillFactory(millFactoryService.getMillFactoryById(factoryId));
    }
    //Crud machine
    @PostMapping("/createMachine")
    public Machine createMachine(@RequestBody Machine machine) throws Exception {
        return machineService.addMachine(machine);
    }
    @GetMapping("/getMachineById")
    public Machine getMachineById(@RequestParam Long machineId) {
        return machineService.getMachineById(machineId);
    }
    @GetMapping("/getAllMachines")
    public List<Machine> getAllMachines() {
        List<Machine> machines = machineService.getMachines();
        return machines;
    }
    @PutMapping("/updateMachine")
    public void updateMachine(@RequestParam  Long machineId,Machine machine){
        machineService.updateMachine(machineId,machine);
    }
    @DeleteMapping("/deleteMachine")
    public void deleteMachine(long machineId){

        machineService.deleteMachine(machineService.getMachineById(machineId));
    }
    //Crud Extraction
    @PostMapping("/createExtraction")
    public Extraction createExtraction(@RequestBody Extraction extraction) throws Exception {
        return extractionService.addExtraction(extraction);
    }
    @GetMapping("/getExtractionById")
    public Extraction getExtractionById(@RequestParam Long extractionId) {
        return extractionService.getExtractionById(extractionId);
    }
    @GetMapping("/getAllExtractions")
    public List<Extraction> getAllExtractions() {
        List<Extraction> extractions = extractionService.getExtractions();
        return extractions;
    }
    @PutMapping("/updateExtraction")
    public void updateExtraction(@RequestParam  Long extractionId,Extraction extraction){
        extractionService.updateExtraction(extractionId,extraction);
    }
    @DeleteMapping("/deleteExtraction")
    public void deleteExtraction(long extractionId){

        extractionService.deleteExtraction(extractionService.getExtractionById(extractionId));
    }
    //Crud oilProductionBatchService
    @PostMapping("/createOilProductionBatch")
    public OilProductionBatch createOilProductionBatch(@RequestBody OilProductionBatch oilProductionBatch) throws Exception {
        return oilProductionBatchService.createOilProductionBatch(oilProductionBatch);
    }
    @GetMapping("/getOilProductionBatchById")
    public OilProductionBatch getOilProductionBatchById(@RequestParam Long oilProductionBatchId) {
        return oilProductionBatchService.getOilProductionBatchById(oilProductionBatchId);
    }
    @GetMapping("/getAllOilProductionBatch")
    public List<OilProductionBatch> getAllOilProductionBatch() {
        List<OilProductionBatch> oilProductionBatchList =oilProductionBatchService.getOilProductionBatches();
        return oilProductionBatchList;
    }
    @PutMapping("/updateOilProductionBatch")
    public void updateOilProductionBatch(@RequestParam  Long oilProductionBatchId,OilProductionBatch oilProductionBatch){
        oilProductionBatchService.updateOilProductionBatch(oilProductionBatchId,oilProductionBatch);
    }
    @DeleteMapping("/deleteOilProductionBatch")
    public void deleteOilProductionBatch(long oilProductionBatchId){

       oilProductionBatchService.deleteOilProductionBatch(oilProductionBatchId);
    }
   // the rest of MillFactory methods
    @GetMapping("/processingHarvest")
    public Extraction processingHarvest(OliveSupplyForExtraction oliveSupplyForExtraction, MillAgreement millAgreement,
                                        Machine machine, Date start_date, Date finishDate,
                                        double waterPer100kg, double averageMixingTime, double pressTemperature,
                                        boolean filtration)
    {
        return millFactoryService.processingHarvest(oliveSupplyForExtraction,millAgreement,machine,start_date,finishDate
                ,waterPer100kg,averageMixingTime,pressTemperature,filtration);
    }
    @GetMapping("/produceOil")
    public OilProductionBatch produceOil(Extraction extraction, AnalysisType analysisType, Boolean isForSale,
                                         double oilQuantity, Owner owner)
    {
        return millFactoryService.produceOil(extraction,analysisType,isForSale,oilQuantity,owner);
    }
    @GetMapping("/purchaseOilFromFarmer")
    public PurchaseOil purchaseOilFromFarmer(Farmer farmer, OilProduct oilProduct, double weight,
                                             MillFactory millFactory, double price)
    {
        return millFactoryService.purchaseOilFromFarmer(farmer,oilProduct,weight,
        millFactory,price);

    }
}
