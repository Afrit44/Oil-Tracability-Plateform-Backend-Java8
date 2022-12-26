//package com.Oil4Med.Oil4Med.DTO;
//
//import com.Oil4Med.Oil4Med.Model.Consumer;
//import com.Oil4Med.Oil4Med.Model.MillAgreement;
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
//public class MillAgreementDTO {
//
//    private Long millAgreementId;
//
//    private Long harvestId;
//
//    private double oliveQuantity;
//
//    private boolean millOlive;
//
//    private double oliveQuantityToMill;
//
//    private boolean sellOlive;
//
//    private double oliveQuantityToSell;
//
//    private Date processingDate;
//
//    public static MillAgreementDTO fromEntity(MillAgreement millAgreement) {
//
//        if (millAgreement == null) {
//            //TODO throw an exception
//        }
//
//        return MillAgreementDTO.builder()
//                .millAgreementId(millAgreement.getMillAgreementId())
//                .harvestId(millAgreement.getHarvestId())
//                .oliveQuantity(millAgreement.getOliveQuantity())
//                .millOlive(millAgreement.isMillOlive())
//                .oliveQuantityToMill(millAgreement.getOliveQuantityToMill())
//                .sellOlive(millAgreement.isSellOlive())
//                .oliveQuantityToSell(millAgreement.getOliveQuantityToSell())
//                .processingDate(millAgreement.getProcessingDate())
//                .build();
//    }
//
//    public static MillAgreement toEntity(MillAgreementDTO millAgreementDTO) {
//
//        if (millAgreementDTO == null) {
//            //TODO throw an exception
//        }
//
//        MillAgreement millAgreement = new MillAgreement();
//        millAgreement.setMillAgreementId(millAgreementDTO.getMillAgreementId());
//        millAgreement.setHarvestId(millAgreementDTO.getHarvestId());
//        millAgreement.setOliveQuantity(millAgreementDTO.getOliveQuantity());
//        millAgreement.setMillOlive(millAgreementDTO.isMillOlive());
//        millAgreement.setOliveQuantityToMill(millAgreementDTO.getOliveQuantityToMill());
//        millAgreement.setSellOlive(millAgreementDTO.isSellOlive());
//        millAgreement.setOliveQuantityToSell(millAgreementDTO.getOliveQuantityToSell());
//        millAgreement.setProcessingDate(millAgreementDTO.getProcessingDate());
//        return millAgreement;
//    }
//}
