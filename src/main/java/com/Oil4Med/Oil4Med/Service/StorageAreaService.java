package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.StorageArea;

import java.util.List;

public interface StorageAreaService {

    List<StorageArea> getStorageArea();
    StorageArea getStorageAreaById(Long id);
    StorageArea addStorageArea(StorageArea storageArea);
    void deleteStorageArea(StorageArea storageArea);
    void updateStorageArea(Long id, StorageArea storageArea);
}
