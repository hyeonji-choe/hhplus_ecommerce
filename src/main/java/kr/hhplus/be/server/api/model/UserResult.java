package kr.hhplus.be.server.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * UserResult
 */
@Getter
@Builder
public class UserResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public UserResult id(Long id) {
        this.id = id;
        return this;
    }

    public static UserResult create(Long id, String name) {
        return UserResult.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static UserResult toResult(User user) {
        return UserResult.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

}