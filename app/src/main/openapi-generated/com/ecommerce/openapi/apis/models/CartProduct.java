package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CartProduct
 */
public class CartProduct  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("quantity")
  private BigDecimal quantity;

  @JsonProperty("added_at")
  private String addedAt;

  public CartProduct id(BigDecimal id) {
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

  public CartProduct quantity(BigDecimal quantity) {
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

  public CartProduct addedAt(String addedAt) {
    this.addedAt = addedAt;
    return this;
  }

  /**
   * Get addedAt
   * @return addedAt
  */

@NotNull   public String getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(String addedAt) {
    this.addedAt = addedAt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartProduct cartProduct = (CartProduct) o;
    return Objects.equals(this.id, cartProduct.id) &&
        Objects.equals(this.quantity, cartProduct.quantity) &&
        Objects.equals(this.addedAt, cartProduct.addedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity, addedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartProduct {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    addedAt: ").append(toIndentedString(addedAt)).append("\n");
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

