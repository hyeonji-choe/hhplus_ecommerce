package kr.hhplus.be.server.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class AssetResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("result")
    private String result;

    public static AssetResult toResult(User user) {
        return AssetResult.builder()
                .id(user.getId())
                .amount(user.getAssetAmount())
                .build();
    }
}
