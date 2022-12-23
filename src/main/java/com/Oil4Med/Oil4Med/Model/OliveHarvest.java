package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.*;
import com.Oil4Med.Oil4Med.Model.Types.Address;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="OliveHarvest")
public class OliveHarvest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "harvestId", nullable = false)
    private Long harvestId;

//    @Column(name = "grove_id", nullable = false)
//    private Long groveId;

    @Column(name = "harvesting_date")
    private Date harvestingDate;

    @Column(name = "harvest_type")
    private MethodOfHarvest harvestType;

    @Column(name = "maturity")
    private OliveMaturity maturity;

    @Column(name = "production_per_hectare")
    private double productionPerHectare;

    @Column(name = "production_per_tree_per_year")
    private double productionPerTreePerYear;

    @Column(name = "avg_transport_before_mill")
    private int avgTransportBeforeMill;

    @Column(name = "frequency_of_production")
    private double frequencyOfProduction;

    @Column(name = "separation")
    private boolean separation;

    @Column(name = "harvest_for_sale")
    private boolean harvestForSale;

    @Column(name = "price")
    private double price;

    @Column(name = "state")
    private State state;

    @Embedded
    @Column(name = "owner_address")
    private Address address;


    @Column(name = "Quantity")
    private double quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="groveId",referencedColumnName = "groveId")
    private OliveGrove oliveGrove;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "oliveHarvest")
    @ElementCollection(targetClass= OliveSupplyForExtraction.class)
    private List<OliveSupplyForExtraction> oliveSupplyForExtractionList;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "oliveHarvest")
    private PurchaseHarvest purchaseHarvest;

}
