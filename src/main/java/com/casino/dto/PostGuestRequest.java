package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostGuestRequest {

    @JsonProperty("grant_type")
    private String grantType;
    private String scope;

}
