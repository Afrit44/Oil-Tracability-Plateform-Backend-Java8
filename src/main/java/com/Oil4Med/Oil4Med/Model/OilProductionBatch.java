package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OilProductionBatch")
public class OilProductionBatch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productionBatchId", nullable = false)
    private Long productionBatchId;

//    @Column(name = "extraction_id", nullable = false)
//    private Long extractionId;

    @Column(name = "analysis_type")
    private AnalysisType analysisType;

    @Column(name = "production_for_sale")
    private boolean productionForSale;

    @Column(name = "oil_quantity")
    private double oilQuantity;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "owner")
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "extractionId",nullable = false,referencedColumnName = "extractionId")
    private Extraction extraction;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "oilProductionBatch")
    private List<OilProduct> oilProductList;
}
