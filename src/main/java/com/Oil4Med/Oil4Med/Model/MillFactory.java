package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.StoringMean;
import com.Oil4Med.Oil4Med.Model.Types.Address;
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
@Table(name="Mill")
public class MillFactory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "millId", nullable = false)
    private Long millId;

    @Column(name="mill_Name")
    private String millName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Column(name = "year_of_creation")
    private int yearOfCreation;

    @Column(name = "milling_capacity")
    private double millingCapacity;

    @Column(name = "storing_mean")
    private StoringMean storingMean;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "number_of_machines")
    private double numberOfMachines;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax")
    private String fax;

    @Column(name = "website")
    private String website;

    @ManyToOne
    @JoinColumn(name = "adminId",referencedColumnName = "adminId")
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "millFactory")
    private List<PurchaseHarvest> purchaseHarvestList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "millFactory")
    private List<MillAgreement> millAgreementList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "millFactory")
    private List<Machine> machines;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "millFactory")
    private MillManager millManager;

}
