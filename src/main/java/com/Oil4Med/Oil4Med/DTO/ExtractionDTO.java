package com.Oil4Med.Oil4Med.DTO;//package com.Oil4Med.Oil4Med.DTO;
//
//import com.Oil4Med.Oil4Med.Model.Consumer;
//import com.Oil4Med.Oil4Med.Model.Enum.ExtractionStatus;
//import com.Oil4Med.Oil4Med.Model.Extraction;
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
//public class ExtractionDTO {
//
//    private Long extractionId;
//
//    private ExtractionStatus extractionStatus;
//
//    private Date startDate;
//
//    private Date finishDate;
//
//    public static ExtractionDTO fromEntity(Extraction extraction) {
//
//        if (extraction == null) {
//            //TODO throw an exception
//        }
//
//        return ExtractionDTO.builder()
//                .extractionId(extraction.getExtractionId())
//                .extractionStatus(extraction.getExtractionStatus())
//                .startDate(extraction.getStartDate())
//                .finishDate(extraction.getFinishDate())
//                .build();
//    }
//
//    public static Extraction toEntity(ExtractionDTO extractionDTO) {
//
//        if (extractionDTO == null) {
//            //TODO throw an exception
//        }
//
//        Extraction extraction = new Extraction();
//        extraction.setExtractionId(extractionDTO.getExtractionId());
//        extraction.setExtractionStatus(extractionDTO.getExtractionStatus());
//        extraction.setStartDate(extractionDTO.getStartDate());
//        extraction.setFinishDate(extractionDTO.getFinishDate());
//        return extraction;
//    }
//}
