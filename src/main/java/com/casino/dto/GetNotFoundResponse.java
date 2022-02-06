package com.casino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetNotFoundResponse {
    public String name;
    public String message;
    public Integer code;
    public Integer status;
}
