package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLogInCreatedPlayerResponse {

    @JsonProperty("token_type")
    public String tokenType;
    @JsonProperty("expires_in")
    public Integer expiresIn;
    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("refresh_token")
    public String refreshToken;

}

