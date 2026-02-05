package com.programacion.distribuida.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
@Getter @Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "placed_on")
    private LocalDate placedOn;

    @Column(name = "delivered_on")
    private LocalDate deliveredOn;

    private BigDecimal total;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}