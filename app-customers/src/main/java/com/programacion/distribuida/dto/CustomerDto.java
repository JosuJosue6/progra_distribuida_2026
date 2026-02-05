package com.programacion.distribuida.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class CustomerDto {

    private Integer id;

    private String name;

    private String email;

    //    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<PurchaseOrderDto> purchaseOrders;
}