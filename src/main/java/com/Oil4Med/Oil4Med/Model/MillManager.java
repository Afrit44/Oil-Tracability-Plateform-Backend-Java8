package com.Oil4Med.Oil4Med.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MillManager")
public class MillManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mill_manager_id", nullable = false)
    private Long millManagerId;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="millId",referencedColumnName = "millId")
    private MillFactory millFactory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="adminId",referencedColumnName = "adminId")
    private Admin admin;
}
