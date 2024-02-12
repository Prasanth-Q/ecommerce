package com.cluodbee.ecommerce;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private ProductDaoService productDaoService;

    public ProductController(ProductDaoService productDaoService) {
        this.productDaoService = productDaoService;
    }

    @PostMapping("/add")
    public ResponseEntity createProduct(@RequestBody Product product) {
        if (product.getProductId() != 0) {
            return ResponseEntity.status(400).body(new ResponseEntityDto("Product Id not accepted"));
        }
        int id = ProductDaoService.productRepo.size() + 1;
        product.setProductId(id);
        return ResponseEntity.status(200).body(productDaoService.createProduct(product));
    }

    @GetMapping("/getall")
    public ResponseEntity getAllProducts() {
        List<Product> listOfProducts = productDaoService.getAllProducts();
        if (listOfProducts.isEmpty()) {
            return ResponseEntity.status(404).body(new ResponseEntityDto("Product Not Found To Delete"));
        }
        return ResponseEntity.status(200).body(listOfProducts);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getProduct(@PathVariable("id") int id) {
        return productDaoService.getProduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        boolean text = productDaoService.deleteProduct(id);
        if (text) {
            return ResponseEntity.status(200).body(new ResponseEntityDto("Product Deleted"));
        }
        return ResponseEntity.status(404).body(new ResponseEntityDto("Product Not Found To Delete"));
    }

    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        return productDaoService.updateProduct(product);
    }

    @PutMapping("/discount")
    public ResponseEntity updateTaxOrDiscount(@RequestBody DiscountOrTax discountOrTax) {
        if (discountOrTax.getDiscount() == 0 && discountOrTax.getTax() == 0) {
            ResponseEntity.status(400).body(new ResponseEntityDto("Tax or Discount must present"));
        } else if (discountOrTax.getDiscount() != 0 && discountOrTax.getTax() != 0) {
            ResponseEntity.status(400).body(new ResponseEntityDto("Tax or Discount any of one only acceptable"));
        }
        if (discountOrTax.getProductId() == 0) {
            return ResponseEntity.status(400).body(new ResponseEntityDto("Product Id must"));
        } else if (productDaoService.getProduct(discountOrTax.getProductId()).getStatusCode() == HttpStatusCode.valueOf(404)) {
            return ResponseEntity.status(404).body(new ResponseEntityDto("Product Id Not found"));
        }
        int x = Math.max(discountOrTax.getTax(), discountOrTax.getDiscount());
        Product product = productDaoService.getProduct1(discountOrTax.getProductId() - 1);
        if (discountOrTax.getDiscount() == 0) {
            product.setPrice(((product.getPrice() / 100) * x) + product.getPrice());
        } else {
            product.setPrice(product.getPrice() - ((product.getPrice() / 100) * x));
        }
        return productDaoService.updateProduct(product);
    }
}
