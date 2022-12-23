package com.Oil4Med.Oil4Med.Controller;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Enum.AnalysisType;
import com.Oil4Med.Oil4Med.Model.Enum.ExtractionStatus;
import com.Oil4Med.Oil4Med.Model.Enum.Owner;
import com.Oil4Med.Oil4Med.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @Autowired
    OliveSupplyForExtractionService oliveSupplyForExtractionService;
    @Autowired
    MillAgreementService millAgreementService;
    @Autowired
    FarmerService farmerService;
    @Autowired
    OilProductService oilProductService;

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
    public Machine createMachine(@RequestParam Long millId, @RequestBody Machine machine) throws Exception {
        machine.setMillFactory(millFactoryService.getMillFactoryById(millId));
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
    @PostMapping("/processingHarvest")
    public Extraction processingHarvest(@RequestParam Long oliveSupplyForExtractionId ,
                                        @RequestParam Long millAgreementId,
                                        @RequestParam Long machineId,
                                        @RequestParam ExtractionStatus extractionStatus,
                                        @RequestParam("Finish_date")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date start_date,
                                        @RequestParam("Start_date")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date finishDate,
                                        @RequestParam double waterPer100kg,
                                        @RequestParam double averageMixingTime,
                                        @RequestParam double pressTemperature,
                                        @RequestParam boolean filtration)
    {
        return millFactoryService.processingHarvest(
                oliveSupplyForExtractionService.getOSupplyForExtractById(oliveSupplyForExtractionId),
                millAgreementService.getMillAgreementById(millAgreementId), extractionStatus,
                machineService.getMachineById(machineId),start_date,finishDate,waterPer100kg,averageMixingTime,
                pressTemperature,filtration);
    }
    @PostMapping("/produceOil")
    public OilProductionBatch produceOil(Long extractionId, AnalysisType analysisType, Boolean isForSale,
                                         double oilQuantity, Owner owner)
    {
        return millFactoryService.produceOil(extractionService.getExtractionById(extractionId),
                analysisType,isForSale,oilQuantity,owner);
    }
    @PostMapping("/purchaseOilFromFarmer")
    public PurchaseOil purchaseOilFromFarmer(Long farmerId, Long oilProductId, double quantity,
                                             Long millFactoryId, double price)
    {
        return millFactoryService.purchaseOilFromFarmer(farmerService.getFarmerById(farmerId),
                oilProductService.getOilProductById(oilProductId),quantity,
                millFactoryService.getMillFactoryById(millFactoryId),price);
    }
}
