package com.macko.services.interfaces;

import java.util.List;

import com.macko.models.Product;

public interface IProductService {
    
    List<Product> getAllProducts();
    
    Product getProductById(long id);
    
    List<Product> serachProductsByName(String name);
    
    void saveProduct(Product product);
    
    void updateProduct(Product product);
    
    void deleteProductById(long id);
}
