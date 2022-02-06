package com.casino.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterPlayerResponse {

    public Integer id;
    @JsonProperty("country_id")
    public String countryId;
    @JsonProperty("timezone_id")
    public String timezoneId;
    public String username;
    public String email;
    public String name;
    public String surname;
    public String gender;
    @JsonProperty("phone_number")
    public String phoneNumber;
    public String birthdate;
    @JsonProperty("bonuses_allowed")
    public Boolean bonusesAllowed;
    @JsonProperty("is_verified")
    public Boolean isVerified;

}
