package com.programacion.distribuida.rest;

import com.programacion.distribuida.dto.CustomerDto;
import com.programacion.distribuida.dto.PurchaseOrderDto;
import com.programacion.distribuida.repo.CustomersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
@AllArgsConstructor
public class CustomerRestController {

    final CustomersRepository customersRepository;

    @GetMapping
    public List<CustomerDto> findAll() {
        return customersRepository.findAll()
                .stream()
                .map(it->{


                    var purchaseOrders = it.getPurchaseOrders().stream()
                            .map(it2 -> {
                                return PurchaseOrderDto.builder()
                                        .id(it2.getId())
                                        .placedOn(it2.getPlacedOn())
                                        .deliveredOn(it2.getDeliveredOn())
                                        .total(it2.getTotal())
                                        .status(it2.getStatus())
                                        .build();
                            })
                            .toList();

                    return CustomerDto.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .email(it.getEmail())
                            .purchaseOrders(purchaseOrders)
                            .build();
                })
                .toList();
    }
}