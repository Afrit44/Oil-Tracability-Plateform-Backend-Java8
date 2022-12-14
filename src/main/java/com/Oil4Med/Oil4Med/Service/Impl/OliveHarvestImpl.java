package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.OliveGrove;
import com.Oil4Med.Oil4Med.Model.OliveHarvest;
import com.Oil4Med.Oil4Med.Repository.OliveGroveRepository;
import com.Oil4Med.Oil4Med.Repository.OliveHarvestRepository;
import com.Oil4Med.Oil4Med.Service.OliveHarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OliveHarvestImpl implements OliveHarvestService {
    @Autowired
    OliveGroveRepository oliveGroveRepository;
    @Autowired
    OliveHarvestRepository oliveHarvestRepository ;
    public OliveHarvestImpl(OliveGroveRepository oliveGroveRepository,OliveHarvestRepository oliveHarvestRepository)
    {
        this.oliveGroveRepository = oliveGroveRepository ;
        this.oliveHarvestRepository =oliveHarvestRepository;
    }
    @Override
    public OliveHarvest createOliveHarvest(Long groveId, OliveHarvest oliveHarvest) {
        OliveGrove oliveGrove = oliveGroveRepository.findById(groveId).get();
        oliveHarvest.setOliveGrove(oliveGrove);
        return (oliveHarvestRepository.save(oliveHarvest));

    }

    @Override
    public OliveHarvest updateOliveHarvest(Long groveId, Long harvestId, OliveHarvest oliveHarvest) {
        OliveHarvest oldHarvest = oliveHarvestRepository.findById(harvestId).get();
        OliveGrove oliveGrove = oliveGroveRepository.findById(groveId).get();
        oldHarvest.setOliveGrove(oliveGrove);
        oldHarvest.setHarvestId(harvestId);
        oldHarvest.setQuantity(oliveHarvest.getQuantity());
        oldHarvest.setHarvestingDate(oliveHarvest.getHarvestingDate());
        oldHarvest.setHarvestType(oliveHarvest.getHarvestType());
        oldHarvest.setPurchaseHarvest(oliveHarvest.getPurchaseHarvest());
        oldHarvest.setAddress(oliveHarvest.getAddress());
        oldHarvest.setAvgTransportBeforeMill(oliveHarvest.getAvgTransportBeforeMill());
        oldHarvest.setHarvestForSale(oliveHarvest.isHarvestForSale());
        oldHarvest.setMaturity(oldHarvest.getMaturity());
        oldHarvest.setFrequencyOfProduction(oliveHarvest.getFrequencyOfProduction());
//        oldHarvest.setPackaging(oliveHarvest.getPackaging());
        oldHarvest.setSeparation(oliveHarvest.isSeparation());
        oldHarvest.setState(oliveHarvest.getState());
        oldHarvest.setProductionPerTreePerYear(oliveHarvest.getProductionPerTreePerYear());
        oldHarvest.setPrice(oliveHarvest.getPrice());

        return oliveHarvestRepository.save(oldHarvest);

    }

    @Override
    public void deleteOliveHarvest(Long harvestId) {
        oliveHarvestRepository.deleteById(harvestId);
    }

    @Override
    public List<OliveHarvest> getOliveHarvests() {
        return oliveHarvestRepository.findAll();
    }

    @Override
    public OliveHarvest getOliveHarvestById(Long harvestId) {
        return oliveHarvestRepository.findById(harvestId).orElse(null);
    }
}
