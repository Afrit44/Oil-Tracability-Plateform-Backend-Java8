package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.OwnershipNature;
import com.Oil4Med.Oil4Med.Model.Enum.TypeOfOlive;
import com.Oil4Med.Oil4Med.Model.Enum.TypeOfSoil;
import com.Oil4Med.Oil4Med.Model.Types.Address;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OliveGrove")
public class OliveGrove implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groveId", nullable = false)
    private Long groveId;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Column(name = "ownership_nature")
    private OwnershipNature ownershipNature;

    @Column(name = "tree_age")
    private int treeAge;

    @Column(name = "total_area")
    private int totalArea;

    @Column(name = "density")
    private Double density;

    @Column(name = "variety_trees")
    private ArrayList<String> varietyTrees;

    @Column(name = "type_of_soil")
    private TypeOfSoil typeOfSoil;

    @Column(name = "fertilization_product")
    private ArrayList<String> fertilizationProduct;

    @Column(name = "field_picture")
    private String fieldPicture;

    @Column(name = "pesticide_sprays")
    private boolean pesticideSprays;

    @Column(name = "type")
    private TypeOfOlive type;

    @Column(name = "irrigation")
    private boolean irrigation;

    @ManyToOne
    @JoinColumn(name = "farmerId",referencedColumnName = "farmerId")
    private Farmer farmer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "oliveGrove")
    private List<OliveHarvest> oliveHarvestList;
}
