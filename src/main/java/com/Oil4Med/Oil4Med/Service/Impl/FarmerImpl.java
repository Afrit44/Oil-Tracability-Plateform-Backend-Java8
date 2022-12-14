package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.*;
import com.Oil4Med.Oil4Med.Repository.FarmerRepository;
import com.Oil4Med.Oil4Med.Service.FarmerService;
import com.Oil4Med.Oil4Med.Service.OilProductService;
import com.Oil4Med.Oil4Med.Service.OliveHarvestService;
import com.Oil4Med.Oil4Med.Service.OliveSupplyForExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Farmer addFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    @Override
    public void deleteFarmer(Farmer farmer) {
        farmerRepository.delete(farmer);
    }

    @Override
    public void updateFarmer(Long farmerId, Farmer newFarmer) {
        Farmer farmer = farmerRepository.findById(farmerId).get();
        farmer.setEmail(newFarmer.getEmail());
        farmer.setLastName(newFarmer.getLastName());
        farmer.setFirstName(newFarmer.getFirstName());
        farmer.setPassword(newFarmer.getPassword());
        farmer.setPhoneNumber(newFarmer.getPhoneNumber());
        farmerRepository.save(farmer);
    }

    @Override
    public OliveSupplyForExtraction transportHarvestToMill(OliveHarvest oliveHarvest, double weight) {
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
