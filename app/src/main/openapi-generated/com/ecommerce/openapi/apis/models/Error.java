package com.ecommerce.openapi.apis.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Error
 */
public class Error  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("value")
  private String value;

  public Error value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */

@NotNull   public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.value, error.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

