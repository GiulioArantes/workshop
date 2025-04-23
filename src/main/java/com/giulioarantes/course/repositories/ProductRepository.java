package com.giulioarantes.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giulioarantes.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
