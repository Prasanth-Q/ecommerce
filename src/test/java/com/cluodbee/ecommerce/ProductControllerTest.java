package com.cluodbee.ecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductDaoService productDaoService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testCreateProduct() {
        Product product = new Product("Test Product", "Description", 10.99f, 100);
        when(productDaoService.createProduct(any(Product.class))).thenReturn(product);

        ResponseEntity responseEntity = productController.createProduct(product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Test Product 1", "Description 1", 10.99f, 100));
        productList.add(new Product("Test Product 2", "Description 2", 20.99f, 200));
        when(productDaoService.getAllProducts()).thenReturn(productList);

        ResponseEntity responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productList, responseEntity.getBody());
    }

    @Test
    public void testGetProduct() {
        int productId = 1;
        Product product = new Product(productId, "Test Product", "Description", 10.99f, 100);
        when(productDaoService.getProduct(productId)).thenReturn(ResponseEntity.ok(product));

        ResponseEntity responseEntity = productController.getProduct(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }


    @Test
    public void testUpdateProduct() {
        Product product = new Product("Test Product", "Description", 10.99f, 100);
        when(productDaoService.updateProduct(any(Product.class))).thenReturn(ResponseEntity.ok(product));

        ResponseEntity responseEntity = productController.updateProduct(product);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

}