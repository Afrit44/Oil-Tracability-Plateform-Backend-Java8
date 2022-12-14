package com.Oil4Med.Oil4Med.DTO;//package com.Oil4Med.Oil4Med.DTO;
//
//import com.Oil4Med.Oil4Med.Model.Consumer;
//import com.Oil4Med.Oil4Med.Model.Machine;
//import jakarta.persistence.Column;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//
//import java.util.Date;
//
//@SuperBuilder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class MachineDTO {
//
//    private Long machineId;
//
//    private String marque;
//
//    private String constructor;
//
//    private Date purchaseDate;
//
//    public static MachineDTO fromEntity(Machine machine) {
//
//        if (machine == null) {
//            //TODO throw an exception
//        }
//
//        return MachineDTO.builder()
//                .machineId(machine.getMachineId())
//                .marque(machine.getMarque())
//                .constructor(machine.getConstructor())
//                .purchaseDate(machine.getPurchaseDate())
//                .build();
//    }
//
//    public static Machine toEntity(MachineDTO machineDTO) {
//
//        if (machineDTO == null) {
//            //TODO throw an exception
//        }
//
//        Machine machine = new Machine();
//        machine.setMachineId(machineDTO.getMachineId());
//        machine.setConstructor(machineDTO.getConstructor());
//        machine.setMarque(machineDTO.getMarque());
//        machine.setPurchaseDate(machineDTO.getPurchaseDate());
//        return machine;
//    }
//}
