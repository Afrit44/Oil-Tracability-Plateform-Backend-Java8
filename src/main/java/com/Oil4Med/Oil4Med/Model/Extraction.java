package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.ExtractionStatus;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Extraction")
public class Extraction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "extractionId", nullable = false)
    private Long extractionId;

    @Column(name = "status")
    private ExtractionStatus extractionStatus;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "finish_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Column(name = "Water_per_100Kg")
    private double waterPer100Kg;

    @Column(name = "average_mixing_time")
    private double averageMixingTime;

    @Column(name = "press_temperature")
    private double pressTemperature;

    @Column(name = "filtration")
    private boolean filtration;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<OliveSupplyForExtraction> oliveSupplyForExtractionList;

    @JsonIgnore
    @ManyToMany
//    @JoinColumn(name="machines_id")
    private List<Machine> machinesList;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "extraction")
    private OilProductionBatch oilProductionBatch;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "extraction")
//    private List<MillAgreement> millAgreementList;
}
