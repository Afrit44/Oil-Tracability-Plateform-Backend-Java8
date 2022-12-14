package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.OliveHarvest;

import java.util.List;

public interface OliveHarvestService {
    public abstract OliveHarvest createOliveHarvest(Long groveId, OliveHarvest oliveHarvest);
    public abstract OliveHarvest updateOliveHarvest(Long groveId,Long harvestId,OliveHarvest oliveHarvest);
    public abstract void deleteOliveHarvest(Long harvestId);
    public abstract List<OliveHarvest> getOliveHarvests();
    public abstract OliveHarvest getOliveHarvestById(Long harvestId);
}
