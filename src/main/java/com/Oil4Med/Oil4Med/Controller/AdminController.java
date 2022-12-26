package com.Oil4Med.Oil4Med.Controller;

import com.Oil4Med.Oil4Med.DTO.AdminDTO;
import com.Oil4Med.Oil4Med.DTO.ConsumerDTO;
import com.Oil4Med.Oil4Med.DTO.FarmerDTO;
import com.Oil4Med.Oil4Med.DTO.MillFactoryDTO;
import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Model.Types.OilTraceability;
import com.Oil4Med.Oil4Med.Repository.AdminRepository;
import com.Oil4Med.Oil4Med.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/oil4med/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private MillFactoryService millFactoryService;
    @Autowired
    private MillManagerService millManagerService ;
    @Autowired
    private AdminRepository adminRepository;

    //CRUD Admin
    @PostMapping("/createAdmin")
    public Admin createAdmin(@RequestBody Admin admin) throws Exception {
        return adminService.addAdmin(admin);
    }
    @GetMapping("/getAdminById/{adminId}")
    public AdminDTO getAdminById(@PathVariable("adminId") Long adminId) {
        return AdminDTO.fromEntity(adminService.getAdminById(adminId));
    }
    @GetMapping("/getAllAdmins")
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminService.getAdmins();
        List<AdminDTO> adminDTOS = new ArrayList<>();
        for (Admin admin : admins){
            adminDTOS.add(AdminDTO.fromEntity(admin));
        }
        return adminDTOS;
    }
    @PutMapping("/updateAdmin")
    public void updateAdmin(@RequestParam  Long adminId,Admin newAdmin){
        adminService.updateAdmin(adminId,newAdmin);
    }
    @DeleteMapping("/deleteAdmin")
    public void deleteAdmin(long adminId){
        adminService.deleteAdmin(adminService.getAdminById(adminId));
    }

    //ConsumerCRUD
    @PostMapping("/createConsumer/{adminId}")
    public Consumer createConsumer(@PathVariable("adminId") Long adminId,@RequestBody Consumer consumer) throws Exception {
        List<Consumer> newConsumerList = adminService.getAdminById(adminId).getConsumers();
        newConsumerList.add(consumer);
        adminService.getAdminById(adminId).setConsumers(newConsumerList);
        //updateAdmin(adminId,adminService.getAdminById(adminId));
        consumer.setAdminCreatedConsumer(adminService.getAdminById(adminId));
        return consumerService.addConsumer(consumer);
    }
    @GetMapping("/getConsumer")
    public ConsumerDTO findConsumerById(@RequestParam Long consumerId) {
        return ConsumerDTO.fromEntity(consumerService.getConsumersById(consumerId));
    }
    @GetMapping("/getAllConsumers")
    public List<ConsumerDTO> getAllConsumers(){
        List<Consumer> consumers = consumerService.getConsumers();
        List<ConsumerDTO> consumerDTOS = new ArrayList<>();
        for (Consumer consumer : consumers){
            consumerDTOS.add(ConsumerDTO.fromEntity(consumer));
        }
        return consumerDTOS;
    }
    @PutMapping("/updateConsumer")
    public void updateConsumer(@RequestParam  Long consumerId,Consumer newConsumer){
        consumerService.updateConsumer(consumerId,newConsumer);
    }
    @DeleteMapping("/deleteConsumer")
    public void deleteConsumer(long consumerId) {
        consumerService.deleteConsumer(consumerService.getConsumersById(consumerId));
    }

    //CRUD Farmer
    @PostMapping("/createFarmer/{adminId}")
    public Farmer createFarmer( @PathVariable("adminId") Long adminId,@RequestBody Farmer farmer) throws Exception {
        List<Farmer> newFarmerList = adminService.getAdminById(adminId).getFarmers();
        newFarmerList.add(farmer);
        adminService.getAdminById(adminId).setFarmers(newFarmerList);
        //updateAdmin(adminId,adminService.getAdminById(adminId));
        farmer.setAdmin(adminService.getAdminById(adminId));
        return farmerService.addFarmer(farmer);
    }
    @GetMapping("/getFarmer")
    public FarmerDTO findFarmerById(@RequestParam Long farmerId) {
        return FarmerDTO.fromEntity(farmerService.getFarmerById(farmerId));
    }
    @GetMapping("/getAllFarmers")
    public List<FarmerDTO> getAllFarmers(){
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
    @DeleteMapping("/deleteFarmer")
    public void deleteFarmer(long farmerId) {
        farmerService.deleteFarmer(farmerService.getFarmerById(farmerId));
    }

    //CRUD MillFactory
    @PostMapping("/createMillFactory/{adminId}")
    public MillFactory createMillFactory(@PathVariable("adminId") Long adminId,@RequestBody MillFactory millFactory) throws Exception {
        List<MillFactory> millFactoryList = adminService.getAdminById(adminId).getMills();
        millFactoryList.add(millFactory);
        adminService.getAdminById(adminId).setMills(millFactoryList);
        //updateAdmin(adminId,adminService.getAdminById(adminId));
        millFactory.setAdmin(adminService.getAdminById(adminId));
        return millFactoryService.addMillFactory(millFactory);
    }
    @GetMapping("/getMillFactory")
    public MillFactoryDTO findMillFactoryById(@RequestParam Long millFactoryId) {
        return MillFactoryDTO.fromEntity(millFactoryService.getMillFactoryById(millFactoryId));
    }
    @GetMapping("/getAllMillFactories")
    public List<MillFactoryDTO> getAllMillFactories(){
        List<MillFactory> millFactories = millFactoryService.getMillFactories();
        List<MillFactoryDTO> millFactoryDTOS = new ArrayList<>();
        for (MillFactory millFactory : millFactories){
            millFactoryDTOS.add(MillFactoryDTO.fromEntity(millFactory));
        }
        return millFactoryDTOS;
    }
    @DeleteMapping("/deleteMillFactory")
    public void deleteMillFactory(long millFactoryId) {
        millFactoryService.deleteMillFactory(millFactoryService.getMillFactoryById(millFactoryId));
    }

    //CRUD MillManager
    @PostMapping("/createMillManager/{adminId}/{millId}")
    public MillManager createMillManager(@PathVariable("adminId") Long adminId, @PathVariable("millId") Long millId, @RequestBody MillManager millManager) throws Exception {
        List<MillManager> newMillManagerList = adminService.getAdminById(adminId).getMillManagerList();
        newMillManagerList.add(millManager);
        adminService.getAdminById(adminId).setMillManagerList(newMillManagerList);
        //updateAdmin(adminId,adminService.getAdminById(adminId));
        millManager.setAdmin(adminService.getAdminById(adminId));
        millManager.setMillFactory(millFactoryService.getMillFactoryById(millId));
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
        MillManager millManager = millManagerService.getMillManagerById(managerId);
        Admin admin = millManager.getAdmin();
//        MillFactory millFactory = millManager.getMillFactory();
        millManager.setAdmin(null);
//        millManager.setMillFactory(null);
        admin.getMillManagerList().remove(millManager);
        admin.setMillManagerList(admin.getMillManagerList());
//        millFactory.setMillManager(null);
//        millFactoryRepository.save(millFactory);
        adminRepository.save(admin);
        millManagerService.deleteMillManager(millManagerService.getMillManagerById(managerId));
    }

//
//    //Rest of Admin functions
//    @GetMapping("/purchaseOilFromFarmer")
//    public PurchaseOil purchaseOilFromFarmer(Consumer consumer, Farmer farmer, OilProduct oilProduct, double quantity, double price) {
//        return consumerService.purchaseOilFromFarmer(consumer,farmer,oilProduct,quantity,price);
//    }
//
//    @GetMapping("/purchaseOilFromMill")
//    public PurchaseOil purchaseOilFromMill(MillFactory millFactory, OilProduct oilProduct, double quantity, double price, Consumer consumer) {
//        return consumerService.purchaseOilFromMill(millFactory,oilProduct,quantity,price,consumer);
//    }
//
//    @GetMapping("/checkTraceability")
//    public OilTraceability checkTraceability(OilProduct oilProduct) {
//        return consumerService.checkTraceability(oilProduct);
//    }
//    @PostMapping("/addOliveRegion")
//    public void addOliveRegion() {
//        adminService.addOliveRegion();
//    }
//
//    @DeleteMapping("/deleteOliveRegion")
//    public void deleteOliveRegion() {
//        adminService.deleteOliveRegion();
//    }
//
//    @PostMapping("/addZone")
//    public void addZone() {
//        adminService.addZone();
//    }
//
//    @PutMapping("/updateZone")
//    public void updateZone() {
//        adminService.updateZone();
//    }
//
//    @DeleteMapping("/deleteZone")
//    public void deleteZone() {
//        adminService.deleteZone();
//    }



}
