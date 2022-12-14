package com.Oil4Med.Oil4Med.DTO;

import com.Oil4Med.Oil4Med.Model.Farmer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerDTO {
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phoneNumber;

    public static FarmerDTO fromEntity(Farmer farmer) {

        if (farmer == null) {
            //TODO throw an exception
        }

        return FarmerDTO.builder()
                .firstName(farmer.getFirstName())
                .lastName(farmer.getLastName())
                .email(farmer.getEmail())
                .phoneNumber(farmer.getPhoneNumber())
                .build();
    }

    public static Farmer toEntity(FarmerDTO farmerDTO) {

        if (farmerDTO == null) {
            //TODO throw an exception
        }

        Farmer farmer = new Farmer();
        farmer.setFirstName(farmerDTO.getFirstName());
        farmer.setLastName(farmerDTO.getLastName());
        farmer.setEmail(farmerDTO.getEmail());
        farmer.setPhoneNumber((farmerDTO.getPhoneNumber()));
        return farmer;
    }
}
