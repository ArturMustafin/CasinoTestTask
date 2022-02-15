package com.casino.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString
public class RegisterPlayerResponse {

    @JsonProperty("bonuses_allowed")
    private boolean bonusesAllowed;

    @JsonProperty("birthdate")
    private Object birthdate;

    @JsonProperty("gender")
    private Object gender;

    @JsonProperty("surname")
    private Object surname;

    @JsonProperty("timezone_id")
    private Object timezoneId;

    @JsonProperty("name")
    private Object name;

    @JsonProperty("phone_number")
    private Object phoneNumber;

    @JsonProperty("id")
    private int id;

    @JsonProperty("is_verified")
    private boolean isVerified;

    @JsonProperty("country_id")
    private Object countryId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

}
