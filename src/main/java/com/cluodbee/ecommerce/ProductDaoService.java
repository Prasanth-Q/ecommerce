package com.cluodbee.ecommerce;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoService {

    public static List<Product> productRepo = new ArrayList<>();

    public Product createProduct(Product product){
        productRepo.add(product);
        int id = product.getProductId();
        return productRepo.stream().filter(a -> a.getProductId() == id).findFirst().get();
    }

    public ResponseEntity getProduct(int id){
        boolean status = productRepo.stream().anyMatch(a -> a.getProductId() == id);
        if(status){
            return ResponseEntity.status(200).body(productRepo.stream().filter(a -> a.getProductId() == id).findFirst().get());
        }
        return ResponseEntity.status(404).body(new ResponseEntityDto("Cant Find given ProductId"));

    }

    public List<Product> getAllProducts(){
        return productRepo;
    }

    public ResponseEntity updateProduct(Product product){
        int id = product.getProductId();
        if(deleteProduct(id)){
            return ResponseEntity.status(200).body(createProduct(product));
        }
        return ResponseEntity.status(200).body(new ResponseEntityDto("Can't find product to delete"));
    }

    public boolean deleteProduct(int id){
        boolean status= productRepo.removeIf(product -> product.getProductId() == id);
        return status;
    }

    public Product getProduct1(int id){

        return productRepo.get(id);

    }
}
