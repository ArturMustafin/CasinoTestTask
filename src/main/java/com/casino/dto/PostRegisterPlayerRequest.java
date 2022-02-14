package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class PostRegisterPlayerRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password_change")
    private String passwordChange;

    @JsonProperty("password_repeat")
    private String passwordRepeat;

    @JsonProperty("email")
    private String email;
}
