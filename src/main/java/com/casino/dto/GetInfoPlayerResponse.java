package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetInfoPlayerResponse {

    public Integer id;
    @JsonProperty("country_id")
    public Object countryId;
    @JsonProperty("timezone_id")
    public Object timezoneId;
    public String username;
    public String email;
    public Object name;
    public Object surname;
    public Object gender;
    @JsonProperty("phone_number")
    public Object phoneNumber;
    public Object birthdate;
    @JsonProperty("bonuses_allowed")
    public Boolean bonusesAllowed;
    @JsonProperty("is_verified")
    public Boolean isVerified;
}
