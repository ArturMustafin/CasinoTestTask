package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterPlayerRequest {
    private String username;
    @JsonProperty("password_change")
    private String passwordChange;
    @JsonProperty("password_repeat")
    private String passwordRepeat;
    private String email;
}
