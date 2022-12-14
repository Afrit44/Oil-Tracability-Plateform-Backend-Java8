package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.Admin;
import com.Oil4Med.Oil4Med.Repository.AdminRepository;
import com.Oil4Med.Oil4Med.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> getAdmins() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }

    @Override
    public void updateAdmin(Long adminId,Admin newAdmin) {
        Admin admin = adminRepository.findById(adminId).get();
        admin.setEmail(newAdmin.getEmail());
        admin.setLastName(newAdmin.getLastName());
        admin.setFirstName(newAdmin.getFirstName());
        admin.setPassword(newAdmin.getPassword());
        adminRepository.save(admin);
    }

    @Override
    public void addOliveRegion() {

    }

    @Override
    public void deleteOliveRegion() {

    }

    @Override
    public void addZone() {

    }

    @Override
    public void updateZone() {

    }

    @Override
    public void deleteZone() {

    }
}
