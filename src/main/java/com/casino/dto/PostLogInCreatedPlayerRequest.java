package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PostLogInCreatedPlayerRequest {

    @JsonProperty("grant_type")
    public String grantType;

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;
}
