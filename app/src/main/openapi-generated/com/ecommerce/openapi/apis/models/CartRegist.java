package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CartRegist
 */
public class CartRegist  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("user_id")
  private BigDecimal userId;

  @JsonProperty("product_id")
  private BigDecimal productId;

  @JsonProperty("quantity")
  private BigDecimal quantity;

  public CartRegist userId(BigDecimal userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */

@NotNull @Valid   public BigDecimal getUserId() {
    return userId;
  }

  public void setUserId(BigDecimal userId) {
    this.userId = userId;
  }

  public CartRegist productId(BigDecimal productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  */

@NotNull @Valid   public BigDecimal getProductId() {
    return productId;
  }

  public void setProductId(BigDecimal productId) {
    this.productId = productId;
  }

  public CartRegist quantity(BigDecimal quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */

@NotNull @Valid   public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartRegist cartRegist = (CartRegist) o;
    return Objects.equals(this.userId, cartRegist.userId) &&
        Objects.equals(this.productId, cartRegist.productId) &&
        Objects.equals(this.quantity, cartRegist.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, productId, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartRegist {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

