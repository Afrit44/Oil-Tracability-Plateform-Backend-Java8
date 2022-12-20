package com.Oil4Med.Oil4Med.Controller;

import com.Oil4Med.Oil4Med.DTO.FarmerDTO;
import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/oil4med/Farmer/")
public class FarmerController {
    @Autowired
    private FarmerService farmerService ;
    @Autowired
    private OliveGroveService oliveGroveService ;
    @Autowired
    private MillAgreementService millAgreementService ;
    @Autowired
    private AdminService adminService ;
    @Autowired
    private OliveHarvestService oliveHarvestService ;
    @Autowired
    private  PurchaseHarvestService purchaseHarvestService ;
    @Autowired
    private OliveSupplyForExtractionService oliveSupplyForExtractionService;
    // farmer CRUD
//    @PostMapping("/createFarmer")
//    public Farmer createFarmer(@RequestBody Farmer farmer) throws Exception {
//
//        return farmerService.addFarmer(farmer);
//    }
    @GetMapping("/getFarmerById")
    public FarmerDTO getFarmerById(@RequestParam Long farmerId) {
        return FarmerDTO.fromEntity(farmerService.getFarmerById(farmerId));
    }
    @GetMapping("/getAllFarmers")
    public List<FarmerDTO> getAllFarmers() {
        List<Farmer> farmers = farmerService.getFarmers();
        List<FarmerDTO> farmerDTOS = new ArrayList<>();
        for (Farmer farmer : farmers){
            farmerDTOS.add(FarmerDTO.fromEntity(farmer));
        }
        return farmerDTOS;
    }
    @PutMapping("/updateFarmer")
    public void updateFarmer(@RequestParam  Long farmerId,Farmer newFarmer){
        farmerService.updateFarmer(farmerId,newFarmer);
    }
//    @DeleteMapping("/deleteFarmer")
//    public void deleteFarmer(long farmerId){
//        farmerService.deleteFarmer(farmerService.getFarmerById(farmerId));
//    }
    // oliveGrove CRUD
    @PostMapping("/createOliveGrove")
    public OliveGrove createFarmer(@RequestParam Long farmerId,@RequestBody OliveGrove oliveGrove) throws Exception {

        return oliveGroveService.createOliveGrove(farmerId,oliveGrove);
    }
    @GetMapping("/getOliveGroveById")
    public OliveGrove getOliveGroveById(@RequestParam Long oliveGroveId) {
        return oliveGroveService.getOliveGroveById(oliveGroveId);
    }
    @GetMapping("/getAllOliveGroves")
    public List<OliveGrove> getAllOliveGroves() {
        List<OliveGrove> oliveGroves = oliveGroveService.getOliveGroves();
        return oliveGroves;
    }
    @PutMapping("/updateOliveGrove")
    public void updateOliveGrove(@RequestParam  Long oliveGroveId,@RequestParam  Long farmerId,OliveGrove oliveGrove){
        oliveGroveService.updateOliveGrove(oliveGroveId,farmerId,oliveGrove);
    }
    @DeleteMapping("/deleteOliveGrove")
    public void deleteOliveGrove(long oliveGroveId){
        oliveGroveService.deleteOliveGrove(oliveGroveId);
    }
    // Mill Agreement CRUD
    @PostMapping("/createMillAgreement")
    public MillAgreement createMillAgreement(@RequestBody MillAgreement millAgreement) throws Exception {

        return millAgreementService.addMillAgreement(millAgreement);
    }
    @GetMapping("/getMillAgreementById")
    public MillAgreement getMillAgreementById(@RequestParam Long millAgreementId) {
        return millAgreementService.getMillAgreementById(millAgreementId);
    }
    @GetMapping("/getAllMillAgreements")
    public List<MillAgreement> getAllMillAgreements() {
        List<MillAgreement> millAgreements = millAgreementService.getMillAgreements();
        return millAgreements;
    }
    @PutMapping("/updateMillAgreement")
    public void updateMillAgreement(@RequestParam  Long millAgreementId,MillAgreement millAgreement){
        millAgreementService.updateMillAgreement(millAgreementId,millAgreement);
    }
    @DeleteMapping("/deleteMillAgreement")
    public void deleteMillAgreement(long millAgreementId){
        millAgreementService.deleteMillAgreement(millAgreementService.getMillAgreementById(millAgreementId));
    }

    // Purchase Harvest CRUD
    @PostMapping("/createPurchaseHarvest")
    public PurchaseHarvest createPurchaseHarvest(@RequestBody PurchaseHarvest purchaseHarvest) throws Exception {

        return purchaseHarvestService.addPurchaseHarvest(purchaseHarvest);
    }
//    @GetMapping("/getPurchaseHarvestById")
//    public PurchaseHarvest getPurchaseHarvestById(@RequestParam Long purchaseHarvestId) {
//        return purchaseHarvestService.getPurchaseHarvestById(purchaseHarvestId);
//    }
//    @GetMapping("/getAllPurchaseHarvests")
//    public List<PurchaseHarvest> getAllPurchaseHarvests() {
//        List<PurchaseHarvest> purchaseHarvests = purchaseHarvestService.getPurchaseHarvest();
//        return purchaseHarvests;
//    }
//    @PutMapping("/updatePurchaseHarvest")
//    public void updatePurchaseHarvest(@RequestParam  Long purchaseHarvestId,PurchaseHarvest purchaseHarvest){
//        purchaseHarvestService.updatePurchaseHarvest(purchaseHarvestId,purchaseHarvest);
//    }
//    @DeleteMapping("/deletePurchaseHarvest")
//    public void deletePurchaseHarvest(long purchaseHarvestId){
//        purchaseHarvestService.deletePurchaseHarvest(purchaseHarvestService.getPurchaseHarvestById(purchaseHarvestId));
//    }


    // Olive Harvest CRUD
    @PostMapping("/createOliveHarvest")
    public OliveHarvest createOliveHarvest(@RequestParam Long groveId,@RequestBody OliveHarvest oliveHarvest) throws Exception {

        return oliveHarvestService.createOliveHarvest(groveId,oliveHarvest);
    }
    @GetMapping("/getOliveHarvestById")
    public OliveHarvest getOliveHarvestById(@RequestParam Long oliveHarvestId) {
        return oliveHarvestService.getOliveHarvestById(oliveHarvestId);
    }
    @GetMapping("/getAllOliveHarvests")
    public List<OliveHarvest> getAllOliveHarvests() {
        List<OliveHarvest> oliveHarvests = oliveHarvestService.getOliveHarvests();
        return oliveHarvests;
    }
    @PutMapping("/updateOliveHarvest")
    public void updateOliveHarvest(@RequestParam  Long groveId,@RequestParam Long oliveHarvestId,OliveHarvest oliveHarvest){
        oliveHarvestService.updateOliveHarvest(groveId,oliveHarvestId,oliveHarvest);
    }
    @DeleteMapping("/deleteOliveHarvest")
    public void deleteOliveHarvest(long oliveHarvestId){
        oliveHarvestService.deleteOliveHarvest(oliveHarvestId);
    }

    // OliveSupplyForExtraction
    @PostMapping("/createOliveSupplyForExtraction")
    public OliveSupplyForExtraction createOliveSupplyForExtraction(@RequestParam Long harvestId, @RequestBody OliveSupplyForExtraction oliveSupplyForExtraction) throws Exception {
        oliveSupplyForExtraction.setOliveHarvest(oliveHarvestService.getOliveHarvestById(harvestId));
        return oliveSupplyForExtractionService.createOSupplyForExtraction(oliveSupplyForExtraction);
    }
    @GetMapping("/getOliveSupplyForExtractionById")
    public OliveSupplyForExtraction getOliveSupplyForExtractionById(@RequestParam Long oliveSId) {
        return oliveSupplyForExtractionService.getOSupplyForExtractById(oliveSId);
    }
    @GetMapping("/getAllOliveSupplyForExtractions")
    public List<OliveSupplyForExtraction> getAllOliveSupplyForExtractions() {
        List<OliveSupplyForExtraction> oliveSExtractions = oliveSupplyForExtractionService.geToSupplyForExtracts();
        return oliveSExtractions;
    }
    @PutMapping("/updateOliveSupplyForExtraction")
    public void updateOliveSupplyForExtraction(@RequestParam  Long oSExtractionId,@RequestParam  Long harvestId,OliveSupplyForExtraction oliveSupplyForExtraction){
        oliveSupplyForExtractionService.updateOSupplyForExtraction(oSExtractionId,harvestId,oliveSupplyForExtraction);
    }
    @DeleteMapping("/deleteOliveSupplyForExtraction")
    public void deleteOliveSupplyForExtraction(long oliveSId){
        oliveSupplyForExtractionService.deleteOSupplyForExtraction(oliveSId);
    }
    @GetMapping("/transportHarvestToMill")
    public OliveSupplyForExtraction transportHarvestToMill(OliveHarvest oliveHarvest, double weight)
    {
        return(farmerService.transportHarvestToMill(oliveHarvest,weight));
    }
    @GetMapping("/sellOil")
    public void sellOil(OilProduct oilProduct, double oilQuantity, Consumer consumer)
    {
        farmerService.sellOil(oilProduct,oilQuantity,consumer);
    }
    @GetMapping("/setIntentionToSellOlive")
    public void setIntentionToSellOlive(OliveHarvest oliveHarvest)
    {
        farmerService.setIntentionToSellOlive(oliveHarvest);
    }
    @GetMapping("/setIntentionToSellOil")
    public void setIntentionToSellOil(OilProduct oilProduct)
    {
        farmerService.setIntentionToSellOil(oilProduct);
    }







}
