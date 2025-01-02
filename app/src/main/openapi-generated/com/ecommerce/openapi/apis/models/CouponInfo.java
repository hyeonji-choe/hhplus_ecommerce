package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CouponInfo
 */
public class CouponInfo  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("coupon_name")
  private String couponName;

  @JsonProperty("benefit_info")
  private Float benefitInfo;

  @JsonProperty("quantity")
  private Double quantity;

  public CouponInfo id(BigDecimal id) {
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

  public CouponInfo couponName(String couponName) {
    this.couponName = couponName;
    return this;
  }

  /**
   * Get couponName
   * @return couponName
  */

@NotNull   public String getCouponName() {
    return couponName;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public CouponInfo benefitInfo(Float benefitInfo) {
    this.benefitInfo = benefitInfo;
    return this;
  }

  /**
   * Get benefitInfo
   * @return benefitInfo
  */

@NotNull   public Float getBenefitInfo() {
    return benefitInfo;
  }

  public void setBenefitInfo(Float benefitInfo) {
    this.benefitInfo = benefitInfo;
  }

  public CouponInfo quantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  */

@NotNull   public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
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
    CouponInfo couponInfo = (CouponInfo) o;
    return Objects.equals(this.id, couponInfo.id) &&
        Objects.equals(this.couponName, couponInfo.couponName) &&
        Objects.equals(this.benefitInfo, couponInfo.benefitInfo) &&
        Objects.equals(this.quantity, couponInfo.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, couponName, benefitInfo, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CouponInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    couponName: ").append(toIndentedString(couponName)).append("\n");
    sb.append("    benefitInfo: ").append(toIndentedString(benefitInfo)).append("\n");
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

