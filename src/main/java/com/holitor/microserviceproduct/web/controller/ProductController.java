package com.holitor.microserviceproduct.web.controller;

import java.util.List;
import java.util.Optional;

import com.holitor.microserviceproduct.model.Product;
import com.holitor.microserviceproduct.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value="/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return (products.isEmpty()) 
            ? new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT) 
            : new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(value
                -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product productAdded =  productRepository.save(product);
        return (productAdded == null)
            ? new ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
            : new ResponseEntity<Product>(productAdded, HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return (!productRepository.existsById(product.getId()))
        ? new ResponseEntity<Product>(HttpStatus.BAD_REQUEST)
        : new ResponseEntity<Product>(productRepository.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable long id) {
        if (!productRepository.findById(id).isPresent())
            return new ResponseEntity<List<Product>>(HttpStatus.BAD_REQUEST);
        productRepository.deleteById(id);
        return new ResponseEntity<List<Product>>((List<Product>) productRepository.findAll(), HttpStatus.OK);
    }
    
}
