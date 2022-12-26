package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> getAdmins();

    Admin getAdminById(Long id);

    Admin addAdmin(Admin admin) throws Exception;

    void deleteAdmin(Admin admin);

    void updateAdmin(Long adminId, Admin newAdmin);

    void addOliveRegion();

    void deleteOliveRegion();

    void addZone();

    void updateZone();

    void deleteZone();

}
