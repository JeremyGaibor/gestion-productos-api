package com.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
