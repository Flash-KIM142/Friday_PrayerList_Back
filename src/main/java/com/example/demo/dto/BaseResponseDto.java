package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BaseResponseDto {

    private int status;
    private String message;

    @JsonProperty("value")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object object;

}
