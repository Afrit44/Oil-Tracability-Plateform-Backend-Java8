package com.Oil4Med.Oil4Med.DTO;

import com.Oil4Med.Oil4Med.Model.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private String firstName;

    private String lastName;

    private String email;

    public static AdminDTO fromEntity(Admin admin) {

        if (admin == null) {
            //TODO throw an exception
        }

        return AdminDTO.builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .build();
    }

    public static Admin toEntity(AdminDTO adminDto) {
        if (adminDto == null) {

            //TODO throw an exception
        }

        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        return admin;
    }
}
