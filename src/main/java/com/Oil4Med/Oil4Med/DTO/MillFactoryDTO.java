package com.Oil4Med.Oil4Med.DTO;

import com.Oil4Med.Oil4Med.Model.Enum.StoringMean;
import com.Oil4Med.Oil4Med.Model.MillFactory;
import com.Oil4Med.Oil4Med.Model.Types.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MillFactoryDTO {


    private String millName;

    private String email;

    private Address address;

    private int yearOfCreation;

    private double millingCapacity;

    private StoringMean storingMean;

    private String managerName;

    private double numberOfMachines;

    private String phoneNumber;

    private String fax;

    private String website;


    public static MillFactoryDTO fromEntity(MillFactory millFactory) {

        if (millFactory == null) {
            //TODO throw an exception
        }

        return MillFactoryDTO.builder()
                .millName(millFactory.getMillName())
                .email(millFactory.getEmail())
                .address(millFactory.getAddress())
                .yearOfCreation(millFactory.getYearOfCreation())
                .millingCapacity(millFactory.getMillingCapacity())
                .storingMean(millFactory.getStoringMean())
                .managerName(millFactory.getManagerName())
                .numberOfMachines(millFactory.getNumberOfMachines())
                .phoneNumber(millFactory.getPhoneNumber())
                .fax(millFactory.getFax())
                .website(millFactory.getWebsite())
                .build();
    }

    public static MillFactory toEntity(MillFactoryDTO millFactoryDTO) {

        if (millFactoryDTO == null) {
            //TODO throw an exception
        }

        MillFactory millFactory = new MillFactory();
        millFactory.setMillName(millFactoryDTO.getMillName());
        millFactory.setEmail(millFactoryDTO.getEmail());
        millFactory.setAddress(millFactoryDTO.getAddress());
        millFactory.setYearOfCreation(millFactoryDTO.getYearOfCreation());
        millFactory.setMillingCapacity(millFactoryDTO.getMillingCapacity());
        millFactory.setStoringMean(millFactoryDTO.getStoringMean());
        millFactory.setManagerName(millFactoryDTO.getManagerName());
        millFactory.setNumberOfMachines(millFactoryDTO.getNumberOfMachines());
        millFactory.setPhoneNumber(millFactoryDTO.getPhoneNumber());
        millFactory.setFax(millFactoryDTO.getFax());
        millFactory.setWebsite(millFactoryDTO.getWebsite());
        return millFactory;
    }
}
