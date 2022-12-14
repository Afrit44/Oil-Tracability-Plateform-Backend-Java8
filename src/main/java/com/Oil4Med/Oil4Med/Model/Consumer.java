package com.Oil4Med.Oil4Med.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Consumer")
public class Consumer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consumerId", nullable = false)
    private Long consumerId;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "consumer")
    private List<PurchaseOil> purchaseOilListByConsumer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="adminId",referencedColumnName = "adminId")
    private Admin adminCreatedConsumer;

}

