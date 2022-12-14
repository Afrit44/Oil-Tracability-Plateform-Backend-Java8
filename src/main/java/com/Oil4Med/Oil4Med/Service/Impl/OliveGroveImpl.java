package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.Farmer;
import com.Oil4Med.Oil4Med.Model.OliveGrove;
import com.Oil4Med.Oil4Med.Repository.FarmerRepository;
import com.Oil4Med.Oil4Med.Repository.OliveGroveRepository;
import com.Oil4Med.Oil4Med.Service.OliveGroveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OliveGroveImpl implements OliveGroveService {
    @Autowired
    OliveGroveRepository oliveGroveRepository ;
    @Autowired
    FarmerRepository farmerRepository;
    public OliveGroveImpl (OliveGroveRepository oliveGroveRepository,FarmerRepository farmerRepository)
    {
        this.oliveGroveRepository = oliveGroveRepository;
        this.farmerRepository = farmerRepository ;
    }
    @Override
    public OliveGrove createOliveGrove(Long farmerId,OliveGrove oliveGrove) {
        Farmer farmer = farmerRepository.findById(farmerId).get();
        oliveGrove.setFarmer(farmer);
        return (oliveGroveRepository.save(oliveGrove));
    }

    @Override
    public OliveGrove updateOliveGrove(Long groveId,Long farmerId, OliveGrove oliveGrove) {
        OliveGrove oldGrove = oliveGroveRepository.findById(groveId).get();
        Farmer oldFarmer = farmerRepository.findById(farmerId).get();
        oldGrove.setFarmer(oldFarmer);
        oldGrove.setGroveId(groveId);
        oldGrove.setAddress(oliveGrove.getAddress());
        oldGrove.setDensity(oliveGrove.getDensity());
        oldGrove.setFertilizationProduct(oliveGrove.getFertilizationProduct());
        oldGrove.setFieldPicture(oliveGrove.getFieldPicture());
        oldGrove.setIrrigation(oliveGrove.isIrrigation());
        oldGrove.setOwnershipNature(oliveGrove.getOwnershipNature());
        oldGrove.setPesticideSprays(oliveGrove.isPesticideSprays());
        oldGrove.setTotalArea(oliveGrove.getTotalArea());
        oldGrove.setTreeAge(oliveGrove.getTreeAge());
        oldGrove.setVarietyTrees(oliveGrove.getVarietyTrees());
        oldGrove.setType(oliveGrove.getType());
        oldGrove.setTypeOfSoil(oliveGrove.getTypeOfSoil());
        return oliveGroveRepository.save(oldGrove);
    }

    @Override
    public void deleteOliveGrove(Long groveId)
    {

        oliveGroveRepository.deleteById(groveId);
    }

    @Override
    public List<OliveGrove> getOliveGroves() {

        return oliveGroveRepository.findAll();
    }

    @Override
    public OliveGrove getOliveGroveById(Long groveId) {

        return oliveGroveRepository.findById(groveId).orElse(null);
    }
}
