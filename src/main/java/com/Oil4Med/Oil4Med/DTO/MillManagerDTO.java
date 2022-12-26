package com.Oil4Med.Oil4Med.DTO;

import com.Oil4Med.Oil4Med.Model.Admin;
import com.Oil4Med.Oil4Med.Model.MillManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MillManagerDTO {
    private String firstName;

    private String lastName;

    private String email;

    public static MillManagerDTO fromEntity(MillManager millManager) {

        if (millManager == null) {
            //TODO throw an exception
        }

        return MillManagerDTO.builder()
                .firstName(millManager.getFirstName())
                .lastName(millManager.getLastName())
                .email(millManager.getEmail())
                .build();
    }

    public static MillManager toEntity(MillManagerDTO millManagerDto) {
        if (millManagerDto == null) {

            //TODO throw an exception
        }

        MillManager millManager = new MillManager();
        millManager.setFirstName(millManagerDto.getFirstName());
        millManager.setLastName(millManagerDto.getLastName());
        millManager.setEmail(millManagerDto.getEmail());
        return millManager;
    }
}
