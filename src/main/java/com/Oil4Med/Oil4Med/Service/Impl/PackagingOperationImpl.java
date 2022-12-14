package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.PackagingOperation;
import com.Oil4Med.Oil4Med.Repository.PackagingOperationRepository;
import com.Oil4Med.Oil4Med.Service.PackagingOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackagingOperationImpl  implements PackagingOperationService {

    @Autowired
    PackagingOperationRepository packagingOperationRepository;

    @Override
    public List<PackagingOperation> getOilPackage() {
        List<PackagingOperation> packagingOperations = new ArrayList<>();
        packagingOperationRepository.findAll().forEach(packagingOperations::add);
        return packagingOperations;
    }

    @Override
    public PackagingOperation getOilPackageById(Long id) {
        return packagingOperationRepository.findById(id).get();
    }

    @Override
    public PackagingOperation addOilPackage(PackagingOperation packagingOperation) {
        return packagingOperationRepository.save(packagingOperation);
    }

    @Override
    public void deleteOilPackage(PackagingOperation packagingOperation) {
        packagingOperationRepository.delete(packagingOperation);
    }

    @Override
    public void updateOilPackage(Long id, PackagingOperation packagingOperation) {
        PackagingOperation packagingOperationFromDb = packagingOperationRepository.findById(id).get();
        packagingOperationFromDb.setPackagingId(packagingOperation.getPackagingId());
        packagingOperationFromDb.setPackagingDate(packagingOperation.getPackagingDate());
        packagingOperationFromDb.setTypeOfPackagingList(packagingOperation.getTypeOfPackagingList());
        packagingOperationFromDb.setCarType(packagingOperation.getCarType());
        packagingOperationFromDb.setTransportDate(packagingOperation.getTransportDate());
        packagingOperationFromDb.setMatricule(packagingOperation.getMatricule());
        packagingOperationRepository.save(packagingOperationFromDb);
    }
}
