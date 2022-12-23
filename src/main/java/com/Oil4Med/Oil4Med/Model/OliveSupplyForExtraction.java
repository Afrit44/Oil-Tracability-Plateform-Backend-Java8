package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.ExtractionType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OliveSupplyForExtraction")
public class OliveSupplyForExtraction implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplyId", nullable = false)
    private Long supplyId;

    @Column(name = "weight")
    private double weight;

    @Column(name = "extractionType")
    private ExtractionType extractionType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="harvestId",nullable = false,referencedColumnName = "harvestId")
    private OliveHarvest oliveHarvest;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="extraction_id")
    private List<Extraction> extractionList;
}
