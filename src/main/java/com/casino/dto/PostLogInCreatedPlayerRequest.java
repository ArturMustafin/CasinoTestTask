package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLogInCreatedPlayerRequest {

    @JsonProperty("grant_type")
    public String grantType;
    public String username;
    public String password;

}
