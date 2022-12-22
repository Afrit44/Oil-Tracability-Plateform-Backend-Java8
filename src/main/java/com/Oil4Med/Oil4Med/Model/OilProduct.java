package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OilProduct")
public class OilProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oilProductId", nullable = false)
    private Long oilProductId;

    @Column(name = "oil_class")
    private OilClass oilClass;

    @Column(name = "analysis_quality_1")
    private AnalysisQuality1 analysisQuality1;

    @Column(name = "analysis_quality_2")
    private AnalysisQuality2 analysisQuality2;

    @Column(name = "analysis_quality_3")
    private AnalysisQuality3 analysisQuality3;

    @Column(name = "analysis_quality_4")
    private AnalysisQuality4 analysisQuality4;

    @Column(name = "analysis_quality_5")
    private AnalysisQuality5 analysisQuality5;

    @Column(name = "analysis_quality_6")
    private AnalysisQuality6 analysisQuality6;

    @Column(name = "oil_quantity")
    private double oilQuantity;

    @Column(name = "oil_product_state")
    private OilProductState oilProductState;

    @Column(name = "storing_state")
    private boolean isStored;

    @Column(name = "packaging_state")
    private boolean isPacked;

    @Column(name = "is_For_Sale")
    private boolean isForSale;


    @ManyToOne
    @JoinColumn(name = "productionBatchId",referencedColumnName = "productionBatchId")
    private OilProductionBatch oilProductionBatch;

    @ManyToOne
    @JoinColumn(name = "packagingId",referencedColumnName = "packagingId")
    private PackagingOperation packagingOperation;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "oilProduct")
    private List<PurchaseOil> purchaseOilList;

//    @ManyToOne
//    @JoinColumn(name = "storageAreaId",nullable = false,referencedColumnName = "storageAreaId")
//    private StorageArea storageArea;

}
