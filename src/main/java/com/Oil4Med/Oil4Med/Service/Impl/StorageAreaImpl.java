package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.StorageArea;
import com.Oil4Med.Oil4Med.Repository.StorageAreaRepository;
import com.Oil4Med.Oil4Med.Service.StorageAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StorageAreaImpl implements StorageAreaService {

    @Autowired
    StorageAreaRepository storageAreaRepository;
    @Override
    public List<StorageArea> getStorageArea() {
        List<StorageArea> storageAreas = new ArrayList<>();
        storageAreaRepository.findAll().forEach(storageAreas::add);
        return storageAreas;
    }

    @Override
    public StorageArea getStorageAreaById(Long id) {
        return storageAreaRepository.findById(id).get();
    }

    @Override
    public StorageArea addStorageArea(StorageArea storageArea) {
        return storageAreaRepository.save(storageArea);
    }

    @Override
    public void deleteStorageArea(StorageArea storageArea) {
        storageAreaRepository.delete(storageArea);
    }

    @Override
    public void updateStorageArea(Long id, StorageArea storageArea) {
        StorageArea storageAreaFromDB = storageAreaRepository.findById(id).get();
        storageAreaFromDB.setStorageAreaId(storageArea.getStorageAreaId());
        storageAreaFromDB.setOwner(storageArea.getOwner());
        storageAreaFromDB.setAddress(storageArea.getAddress());
        storageAreaFromDB.setTemperature(storageArea.getTemperature());
        storageAreaFromDB.setHumidity(storageArea.getHumidity());
        storageAreaRepository.save(storageArea);
    }
}
