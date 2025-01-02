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
 * Asset
 */
public class Asset  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("amount")
  private Double amount;

  @JsonProperty("result")
  private String result;

  @JsonProperty("additionalProperties")
  @Valid
  private Map<String, String> additionalProperties = null;

  public Asset id(BigDecimal id) {
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

  public Asset amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */

@NotNull   public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Asset result(String result) {
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

  public Asset additionalProperties(Map<String, String> additionalProperties) {
    this.additionalProperties = additionalProperties;
    return this;
  }

  public Asset putAdditionalPropertiesItem(String key, String additionalPropertiesItem) {
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
    Asset asset = (Asset) o;
    return Objects.equals(this.id, asset.id) &&
        Objects.equals(this.amount, asset.amount) &&
        Objects.equals(this.result, asset.result) &&
        Objects.equals(this.additionalProperties, asset.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, result, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Asset {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

