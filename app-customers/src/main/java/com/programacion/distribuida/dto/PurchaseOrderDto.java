package com.programacion.distribuida.dto;

import com.programacion.distribuida.db.Customer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@Builder
public class PurchaseOrderDto {

    private Integer id;

    private LocalDate placedOn;

    private LocalDate deliveredOn;

    private BigDecimal total;

    private Integer status;


}
