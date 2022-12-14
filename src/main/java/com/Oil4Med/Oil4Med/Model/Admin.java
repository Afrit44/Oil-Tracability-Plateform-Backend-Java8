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
@Table(name="Admin")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adminId", nullable = false)
    private Long adminId;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Farmer> farmers;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "adminCreatedConsumer")
    private List<Consumer> consumers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MillManager> millManagerList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MillFactory> mills;

}
