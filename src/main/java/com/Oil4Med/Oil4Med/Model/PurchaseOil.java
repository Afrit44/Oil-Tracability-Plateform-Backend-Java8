package com.Oil4Med.Oil4Med.Model;

import com.Oil4Med.Oil4Med.Model.Enum.Buyer;
import com.Oil4Med.Oil4Med.Model.Enum.Seller;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PurchaseOil")
public class PurchaseOil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchaseOilId", nullable = false)
    private Long purchaseId;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @Column(name = "buyer_type")
    private Buyer buyerType;

    @Column(name = "seller_type")
    private Seller sellerType;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "consumerId",nullable = false,referencedColumnName = "consumerId")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "oilProductId",nullable = false,referencedColumnName = "oilProductId")
    private OilProduct oilProduct;
}
