//package com.Oil4Med.Oil4Med.Controller.Authentication;
//
//import com.Oil4Med.Oil4Med.DTO.auth.AuthenticationRequest;
//import com.Oil4Med.Oil4Med.DTO.auth.AuthenticationResponse;
//import com.Oil4Med.Oil4Med.Model.Admin;
//import com.Oil4Med.Oil4Med.Model.Consumer;
//import com.Oil4Med.Oil4Med.Model.Farmer;
//import com.Oil4Med.Oil4Med.Model.MillManager;
//import com.Oil4Med.Oil4Med.Repository.AdminRepository;
//import com.Oil4Med.Oil4Med.Repository.ConsumerRepository;
//import com.Oil4Med.Oil4Med.Repository.FarmerRepository;
//import com.Oil4Med.Oil4Med.Repository.MillManagerRepository;
//import com.Oil4Med.Oil4Med.Service.Authentication.ApplicationUserDetailsService;
//import com.Oil4Med.Oil4Med.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/api/auth/")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private ApplicationUserDetailsService applicationUserDetailsService;
//    @Autowired
//    AdminRepository adminRepository;
//    @Autowired
//    ConsumerRepository consumerRepository;
//    @Autowired
//    FarmerRepository farmerRepository;
//    @Autowired
//    MillManagerRepository millManagerRepository;
//    @Autowired
//    JwtUtil jwtUtil;
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        final UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(request.getEmail());
//        if (adminRepository.findAdminByEmail(request.getEmail())!=null){
//            final String jwt = jwtUtil.generateToken(userDetails) ;
//            return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwt).role("Admin").build());
//        } else if (consumerRepository.findConsumerByEmail(request.getEmail())!=null) {
//            final String jwt = jwtUtil.generateToken(userDetails) ;
//            return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwt).role("Conusmer").build());
//        } else if (farmerRepository.findFarmerByEmail(request.getEmail())!=null) {
//            final String jwt = jwtUtil.generateToken(userDetails) ;
//            return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwt).role("Farmer").build());
//        } else if (millManagerRepository.findMillManagerByEmail(request.getEmail())!=null) {
//            final String jwt = jwtUtil.generateToken(userDetails) ;
//            return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken(jwt).role("MillManager").build());
//        }
//        return null;
//    }
//
//}