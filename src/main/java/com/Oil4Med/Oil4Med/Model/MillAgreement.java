package com.Oil4Med.Oil4Med.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MillAgreement")
public class MillAgreement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "millAgreementId", nullable = false)
    private Long millAgreementId;

    @Column(name = "olive_quantity")
    private double oliveQuantity;

    @Column(name = "mill_olive")
    private boolean millOlive;

    @Column(name = "olive_quantity_to_mill")
    private double oliveQuantityToMill;

    @Column(name = "sell_olive")
    private boolean sellOlive;

    @Column(name = "olive_quantity_to_sell")
    private double oliveQuantityToSell;

    @Column(name = "processing_date")
    private Date processingDate;

    @Column(name = "harvest_id")
    private Long harvestId;

    @ManyToOne
    @JoinColumn(name="farmerId",referencedColumnName = "farmerId")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name="millId",referencedColumnName = "millId")
    private MillFactory millFactory;

//    @ManyToOne
//    @JoinColumn(name="extractionId",referencedColumnName = "extractionId")
//    private Extraction extraction;

}
