package com.ecommerce.openapi.apis.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrderProduct
 */
public class OrderProduct  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private BigDecimal id;

  @JsonProperty("ordered_at")
  private String orderedAt;

  @JsonProperty("order_list")
  @Valid
  private List<@Valid CartProduct> orderList = new ArrayList<>();

  @JsonProperty("result")
  private String result;

  @JsonProperty("additionalProperties")
  @Valid
  private Map<String, String> additionalProperties = null;

  public OrderProduct id(BigDecimal id) {
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

  public OrderProduct orderedAt(String orderedAt) {
    this.orderedAt = orderedAt;
    return this;
  }

  /**
   * Get orderedAt
   * @return orderedAt
  */

@NotNull   public String getOrderedAt() {
    return orderedAt;
  }

  public void setOrderedAt(String orderedAt) {
    this.orderedAt = orderedAt;
  }

  public OrderProduct orderList(List<@Valid CartProduct> orderList) {
    this.orderList = orderList;
    return this;
  }

  public OrderProduct addOrderListItem(CartProduct orderListItem) {
    if (this.orderList == null) {
      this.orderList = new ArrayList<>();
    }
    this.orderList.add(orderListItem);
    return this;
  }

  /**
   * Get orderList
   * @return orderList
  */

@NotNull @Valid   public List<@Valid CartProduct> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<@Valid CartProduct> orderList) {
    this.orderList = orderList;
  }

  public OrderProduct result(String result) {
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

  public OrderProduct additionalProperties(Map<String, String> additionalProperties) {
    this.additionalProperties = additionalProperties;
    return this;
  }

  public OrderProduct putAdditionalPropertiesItem(String key, String additionalPropertiesItem) {
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
    OrderProduct orderProduct = (OrderProduct) o;
    return Objects.equals(this.id, orderProduct.id) &&
        Objects.equals(this.orderedAt, orderProduct.orderedAt) &&
        Objects.equals(this.orderList, orderProduct.orderList) &&
        Objects.equals(this.result, orderProduct.result) &&
        Objects.equals(this.additionalProperties, orderProduct.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderedAt, orderList, result, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderProduct {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orderedAt: ").append(toIndentedString(orderedAt)).append("\n");
    sb.append("    orderList: ").append(toIndentedString(orderList)).append("\n");
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

