package com.holitor.microserviceproduct.repository;

import com.holitor.microserviceproduct.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> { }
