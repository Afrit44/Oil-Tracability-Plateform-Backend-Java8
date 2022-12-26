//package com.Oil4Med.Oil4Med.Service.Authentication;
//
//import com.Oil4Med.Oil4Med.Model.Admin;
//import com.Oil4Med.Oil4Med.Model.Consumer;
//import com.Oil4Med.Oil4Med.Model.Farmer;
//import com.Oil4Med.Oil4Med.Model.MillManager;
//import com.Oil4Med.Oil4Med.Repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class ApplicationUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    AdminRepository adminRepository;
//    @Autowired
//    ConsumerRepository consumerRepository;
//    @Autowired
//    FarmerRepository farmerRepository;
//    @Autowired
//    MillManagerRepository millManagerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        //find the user & it's role based on it's email
//        if (adminRepository.findAdminByEmail(email)!=null){
//            Admin admin = adminRepository.findAdminByEmail(email);
//            return new User(admin.getEmail(),admin.getPassword(), Collections.emptyList());
//        } else if (consumerRepository.findConsumerByEmail(email)!=null) {
//            Consumer consumer = consumerRepository.findConsumerByEmail(email);
//            return new User(consumer.getEmail(),consumer.getPassword(), Collections.emptyList());
//        } else if (farmerRepository.findFarmerByEmail(email)!=null) {
//            Farmer farmer = farmerRepository.findFarmerByEmail(email);
//            return new User(farmer.getEmail(),farmer.getPassword(), Collections.emptyList());
//        } else if (millManagerRepository.findMillManagerByEmail(email)!=null) {
//            MillManager millManager = millManagerRepository.findMillManagerByEmail(email);
//            return new User(millManager.getEmail(),millManager.getPassword(), Collections.emptyList());
//        }
//
//        return null;
//    }
//
//}