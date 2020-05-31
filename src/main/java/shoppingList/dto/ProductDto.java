package shoppingList.dto;

import shoppingList.domain.ProductCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ProductDto {

    private Long productId;
    private String productName;
    private BigDecimal productRegularPrice;
    private ProductCategory productCategory;
    private BigDecimal productDiscount;
    private String productDescription;
    private BigDecimal productActualPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductRegularPrice() {
        return productRegularPrice;
    }

    public void setProductRegularPrice(BigDecimal productRegularPrice) {
        this.productRegularPrice = productRegularPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductActualPrice() {
        return productActualPrice;
    }

    public void setProductActualPrice() {
        if(productDiscount.equals(BigDecimal.valueOf(0.00))){
            this.productActualPrice = productRegularPrice.setScale(3, RoundingMode.HALF_UP);
        }else {
            this.productActualPrice =  productRegularPrice.multiply
                    (BigDecimal.valueOf(1.00).subtract(productDiscount.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))).setScale(3, RoundingMode.HALF_UP);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productRegularPrice, that.productRegularPrice) &&
                productCategory == that.productCategory &&
                Objects.equals(productDiscount, that.productDiscount) &&
                Objects.equals(productDescription, that.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productRegularPrice, productCategory, productDiscount, productDescription);
    }

    @Override
    public String toString() {
        return "ID = " + productId +
                ", Product Name= '" + productName + '\'' +
                ", Regular price= " + productRegularPrice+ " EUR" +
                ", Product category= '" + productCategory + '\'' +
                ", Discount= " + productDiscount+ " %" +
                ", Actual price= " + productActualPrice + " EUR" +
                ", Description= '" + productDescription +
                '\'' + ";";
    }
}
