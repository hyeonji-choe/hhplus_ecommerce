package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegistCouponResult
 */
public class RegistCouponResult  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("coupon_name")
  private String couponName;

  @JsonProperty("result")
  private String result;

  @JsonProperty("additionalProperties")
  @Valid
  private Map<String, String> additionalProperties = null;

  public RegistCouponResult id(BigDecimal id) {
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

  public RegistCouponResult couponName(String couponName) {
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

  public RegistCouponResult result(String result) {
    this.result = result;
    return this;
  }

  /**
   * 여부 실패시, additionalProperties 에 failedReason 포함됨
   * @return result
  */

@NotNull   public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public RegistCouponResult additionalProperties(Map<String, String> additionalProperties) {
    this.additionalProperties = additionalProperties;
    return this;
  }

  public RegistCouponResult putAdditionalPropertiesItem(String key, String additionalPropertiesItem) {
    if (this.additionalProperties == null) {
      this.additionalProperties = new HashMap<>();
    }
    this.additionalProperties.put(key, additionalPropertiesItem);
    return this;
  }

  /**
   * 추가 되어질 수 있는 내역 담기
   * @return additionalProperties
  */

  public Map<String, String> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, String> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistCouponResult registCouponResult = (RegistCouponResult) o;
    return Objects.equals(this.id, registCouponResult.id) &&
        Objects.equals(this.couponName, registCouponResult.couponName) &&
        Objects.equals(this.result, registCouponResult.result) &&
        Objects.equals(this.additionalProperties, registCouponResult.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, couponName, result, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistCouponResult {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    couponName: ").append(toIndentedString(couponName)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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

