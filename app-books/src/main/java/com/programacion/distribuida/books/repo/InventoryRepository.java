package com.programacion.distribuida.books.repo;

import com.programacion.distribuida.books.db.Inventory;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryRepository implements PanacheRepositoryBase<Inventory, String> {
}