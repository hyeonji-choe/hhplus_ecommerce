package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FamousProduct
 */
public class FamousProduct  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("product_name")
  private String productName;

  @JsonProperty("sale_price")
  private Double salePrice;

  @JsonProperty("stocks")
  private BigDecimal stocks;

  @JsonProperty("sell_amount")
  private BigDecimal sellAmount;

  public FamousProduct id(BigDecimal id) {
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

  public FamousProduct productName(String productName) {
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

  public FamousProduct salePrice(Double salePrice) {
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

  public FamousProduct stocks(BigDecimal stocks) {
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

  public FamousProduct sellAmount(BigDecimal sellAmount) {
    this.sellAmount = sellAmount;
    return this;
  }

  /**
   * Get sellAmount
   * @return sellAmount
  */

@NotNull @Valid   public BigDecimal getSellAmount() {
    return sellAmount;
  }

  public void setSellAmount(BigDecimal sellAmount) {
    this.sellAmount = sellAmount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FamousProduct famousProduct = (FamousProduct) o;
    return Objects.equals(this.id, famousProduct.id) &&
        Objects.equals(this.productName, famousProduct.productName) &&
        Objects.equals(this.salePrice, famousProduct.salePrice) &&
        Objects.equals(this.stocks, famousProduct.stocks) &&
        Objects.equals(this.sellAmount, famousProduct.sellAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productName, salePrice, stocks, sellAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FamousProduct {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    salePrice: ").append(toIndentedString(salePrice)).append("\n");
    sb.append("    stocks: ").append(toIndentedString(stocks)).append("\n");
    sb.append("    sellAmount: ").append(toIndentedString(sellAmount)).append("\n");
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

