package com.macko.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.macko.models.Product;

public interface IProductService {
    
    List<Product> getAllProducts();
    
    Product getProductById(UUID id);
    
    List<Product> serachProductsByName(String name);
    
    void saveProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProductById(UUID id);
}
