package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostGuestResponse {
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("access_token")
    private String accessToken;

}
