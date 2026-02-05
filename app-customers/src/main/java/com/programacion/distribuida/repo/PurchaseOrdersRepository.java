package com.programacion.distribuida.repo;

import com.programacion.distribuida.db.Customer;
import com.programacion.distribuida.db.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseOrdersRepository extends JpaRepository<PurchaseOrder, Integer> {

}