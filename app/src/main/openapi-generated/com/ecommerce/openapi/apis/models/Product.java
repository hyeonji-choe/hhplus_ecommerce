package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Product
 */
public class Product  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("product_name")
  private String productName;

  @JsonProperty("sale_price")
  private Double salePrice;

  @JsonProperty("stocks")
  private BigDecimal stocks;

  public Product id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */

@NotNull @Valid   public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public Product productName(String productName) {
    this.productName = productName;
    return this;
  }

  /**
   * Get productName
   * @return productName
  */

@NotNull   public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Product salePrice(Double salePrice) {
    this.salePrice = salePrice;
    return this;
  }

  /**
   * Get salePrice
   * @return salePrice
  */

@NotNull   public Double getSalePrice() {
    return salePrice;
  }

  public void setSalePrice(Double salePrice) {
    this.salePrice = salePrice;
  }

  public Product stocks(BigDecimal stocks) {
    this.stocks = stocks;
    return this;
  }

  /**
   * Get stocks
   * @return stocks
  */

@NotNull @Valid   public BigDecimal getStocks() {
    return stocks;
  }

  public void setStocks(BigDecimal stocks) {
    this.stocks = stocks;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.id, product.id) &&
        Objects.equals(this.productName, product.productName) &&
        Objects.equals(this.salePrice, product.salePrice) &&
        Objects.equals(this.stocks, product.stocks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productName, salePrice, stocks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    salePrice: ").append(toIndentedString(salePrice)).append("\n");
    sb.append("    stocks: ").append(toIndentedString(stocks)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

