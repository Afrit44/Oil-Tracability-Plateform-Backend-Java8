package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.CarType;
import com.Oil4Med.Oil4Med.Model.Enum.TypeOfPackaging;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PackagingOperation")
public class PackagingOperation implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "packagingId", nullable = false)
    private Long packagingId;

    @Column(name = "packaging_date")
    private Date packagingDate;

    @Column(name = "type_of_package")
    private ArrayList<TypeOfPackaging> typeOfPackagingList;

    @Column(name = "transport_date")
    private Date transportDate;

    @Column(name = "car_type")
    private CarType carType;

    @Column(name = "matricule")
    private String matricule;


    @OneToMany(cascade = CascadeType.ALL,mappedBy ="packagingOperation")
    private List<OilProduct> oilProductList;
}
