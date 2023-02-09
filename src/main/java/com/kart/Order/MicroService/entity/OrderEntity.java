package com.kart.Order.MicroService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderEntity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "Product_Id")
    private long productId;
    @Column(name = "Quantity")
    private long quantity;
    @Column(name = "Oder_Date")
    private Instant orderDate;
    @Column(name = "Order_Status")
    private String orderStatus;
    @Column(name = "Total_amount")
    private long amount;



}
