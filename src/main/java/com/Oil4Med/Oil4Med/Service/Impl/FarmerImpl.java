package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.DTO.AdminDTO;
import com.Oil4Med.Oil4Med.DTO.FarmerDTO;
import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Repository.AdminRepository;
import com.Oil4Med.Oil4Med.Repository.FarmerRepository;
import com.Oil4Med.Oil4Med.Repository.OliveHarvestRepository;
import com.Oil4Med.Oil4Med.Service.FarmerService;
import com.Oil4Med.Oil4Med.Service.OilProductService;
import com.Oil4Med.Oil4Med.Service.OliveHarvestService;
import com.Oil4Med.Oil4Med.Service.OliveSupplyForExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmerImpl implements FarmerService {

    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    OliveSupplyForExtractionService oliveSupplyForExtractionService;
    @Autowired
    OliveHarvestService oliveHarvestService;
    @Autowired
    OilProductService oilProductService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    OliveHarvestRepository oliveHarvestRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Farmer> getFarmers() {
        List<Farmer> farmers = new ArrayList<>();
        farmerRepository.findAll().forEach(farmers::add);
        return farmers;
    }
    @Override
    public Farmer getFarmerById(Long id) {
        return farmerRepository.findById(id).get();
    }

    @Override
    public Farmer addFarmer(Farmer farmer) throws Exception {
        if (farmer == null) {
            throw new EntityNotFoundException("ID_IS_NULL");
        }
        //Check email is unique
        List<String> listEmails = new ArrayList<>();
        List<FarmerDTO> farmers = farmerRepository.findAll().stream()
                .map(FarmerDTO::fromEntity).collect(Collectors.toList());
        for (FarmerDTO farmerDTO : farmers){
            listEmails.add(farmerDTO.getEmail());
        }
        if(listEmails.contains(farmer.getEmail())){
            throw new Exception("Email already exists in DataBase");
        }
        farmer.setPassword(passwordEncoder.encode(farmer.getPassword()));
        return farmerRepository.save(farmer);
    }

    @Override
    public void deleteFarmer(Farmer farmer) {
        deleteFarmerAdminRelation(farmer);
        farmerRepository.delete(farmer);

    }

    //We have to delete the foreign key in admin_Farmers table in order to fully delete the Farmer user otherwise
    //it raise ERROR : update or delete on table "farmer" violates foreign key constraint on table "admin_farmers"
    private void deleteFarmerAdminRelation(Farmer farmer) {
        Admin admin = farmer.getAdmin();
        farmer.setAdmin(null);
        List<Farmer> listOfFarmersByAdmin = admin.getFarmers();
        listOfFarmersByAdmin.remove(farmer);
        admin.setFarmers(listOfFarmersByAdmin);
        adminRepository.save(admin);
        farmerRepository.save(farmer);
    }

    @Override
    public void updateFarmer(Long farmerId, Farmer newFarmer) {
        Farmer farmer = farmerRepository.findById(farmerId).get();
        farmer.setEmail(newFarmer.getEmail());
        farmer.setLastName(newFarmer.getLastName());
        farmer.setFirstName(newFarmer.getFirstName());
        farmer.setPassword(newFarmer.getPassword());
        farmer.setPhoneNumber(newFarmer.getPhoneNumber());
//        farmer.setAddress(newFarmer.getAddress());
        farmerRepository.save(farmer);
    }

    @Override
    public OliveSupplyForExtraction transportHarvestToMill(Long harvestId, double weight) {
        OliveHarvest oliveHarvest = oliveHarvestRepository.getById(harvestId);
        //Subtract the weight from the initial harvest
        if (oliveHarvest.getQuantity()-weight<0){
            //Quantity subtracted is more than the one already in hand.
            throw new RuntimeException();
        }
        oliveHarvest.setQuantity(oliveHarvest.getQuantity()-weight);
        oliveHarvestService.updateOliveHarvest(oliveHarvest.getOliveGrove().getGroveId(), oliveHarvest.getHarvestId(),oliveHarvest);
        //Creating the olive Supply as it was transported from harvest to the mill in order to be extracted
        OliveSupplyForExtraction oliveSupplyForExtraction = new OliveSupplyForExtraction();
        oliveSupplyForExtraction.setWeight(weight);
        oliveSupplyForExtraction.setExtractionType(null);
        oliveSupplyForExtraction.setOliveHarvest(oliveHarvest);
        return oliveSupplyForExtractionService.createOSupplyForExtraction(oliveSupplyForExtraction);
    }

    @Override
    public void sellOil(OilProduct oilProduct, double oilQuantity, Consumer consumer) {
        //TODO
    }

    @Override
    public void setIntentionToSellOlive(OliveHarvest oliveHarvest) {
        oliveHarvest.setHarvestForSale(true);
        oliveHarvestService.updateOliveHarvest(oliveHarvest.getOliveGrove().getGroveId(),oliveHarvest.getHarvestId(),oliveHarvest);
    }

    @Override
    public void setIntentionToSellOil(OilProduct oilProduct) {
        oilProduct.setForSale(true);
        oilProductService.updateOilProduct(oilProduct.getOilProductId(), oilProduct);
    }
}
