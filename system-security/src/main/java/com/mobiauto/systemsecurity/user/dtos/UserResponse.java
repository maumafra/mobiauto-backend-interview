package com.mobiauto.systemsecurity.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@JsonPropertyOrder({
        "id", "username", "fullName", "email", "resaleId", "role"
})
public class UserResponse extends RepresentationModel<UserResponse> {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("role")
    private String role;
    @JsonProperty("resaleId")
    private Integer resaleId;
}
