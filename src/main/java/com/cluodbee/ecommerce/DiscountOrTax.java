package com.cluodbee.ecommerce;

public class DiscountOrTax {

    private int productId;

    private int tax;

    private int discount;

    public DiscountOrTax() {}

    public DiscountOrTax(int productId, int tax, int discount) {
        this.productId = productId;
        this.tax = tax;
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
