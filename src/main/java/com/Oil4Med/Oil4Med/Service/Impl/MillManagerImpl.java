package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.DTO.AdminDTO;
import com.Oil4Med.Oil4Med.DTO.MillManagerDTO;
import com.Oil4Med.Oil4Med.Model.Admin;
import com.Oil4Med.Oil4Med.Model.Farmer;
import com.Oil4Med.Oil4Med.Model.MillFactory;
import com.Oil4Med.Oil4Med.Model.MillManager;
import com.Oil4Med.Oil4Med.Repository.AdminRepository;
import com.Oil4Med.Oil4Med.Repository.MillFactoryRepository;
import com.Oil4Med.Oil4Med.Repository.MillManagerRepository;
import com.Oil4Med.Oil4Med.Service.MillManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MillManagerImpl implements MillManagerService {

    @Autowired
    MillManagerRepository millManagerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminImpl adminImpl;
    @Autowired
    FarmerImpl farmerImpl;
    @Autowired
    ConsumerImpl consumerImpl;

    @Override
    public List<MillManager> getMillManagers() {
        List<MillManager> millManagerList = new ArrayList<>();
        millManagerRepository.findAll().forEach(millManagerList::add);
        return millManagerList;
    }

    @Override
    public MillManager getMillManagerById(Long id) {
        return millManagerRepository.findById(id).get();
    }

    @Override
    public MillManager addMillManager(MillManager millManager) throws Exception {
        if (millManager == null) {
            throw new EntityNotFoundException("ID_IS_NULL");
        }
        //Check email is unique

        if(consumerImpl.checkEmailExistInConsumer(millManager.getEmail()) ||
                adminImpl.checkEmailExistAsAdminEmail(millManager.getEmail()) ||
                farmerImpl.checkEmailExistInFarmer(millManager.getEmail()) ||
                checkEmailExitMillManager(millManager.getEmail())){
            throw new Exception("Email already exists in DataBase");
        }
        millManager.setPassword(passwordEncoder.encode(millManager.getPassword()));
        return millManagerRepository.save(millManager);
    }

    boolean checkEmailExitMillManager(String email){
        List<String> listEmails = new ArrayList<>();
        List<MillManagerDTO> millManagerDTOS = millManagerRepository.findAll().stream()
                .map(MillManagerDTO::fromEntity).collect(Collectors.toList());
        for (MillManagerDTO millManagerDTO : millManagerDTOS){
            listEmails.add(millManagerDTO.getEmail());
        }
        if(listEmails.contains(email)){
            return true;
        }
        return false;
    }
    @Override
    public void deleteMillManager(MillManager millManager) {
        deleteMillManagerAdminRelation(millManager);
        millManagerRepository.delete(millManager);
    }

    //We have to delete the foreign key in admin_Farmers table in order to fully delete the Farmer user otherwise
    //it raise ERROR : update or delete on table "farmer" violates foreign key constraint on table "admin_farmers"
    private void deleteMillManagerAdminRelation(MillManager millManager) {
        millManagerRepository.save(millManager);
    }
    @Override
    public void updateMillManager(Long id, MillManager millManager) {
        MillManager millManagerFromDB = millManagerRepository.findById(id).get();
        millManagerFromDB.setFirstName(millManager.getFirstName());
        millManagerFromDB.setLastName(millManager.getLastName());
        millManagerFromDB.setEmail(millManager.getEmail());
        millManagerFromDB.setPassword(millManagerFromDB.getPassword());
        millManagerRepository.save(millManagerFromDB);
    }
}
