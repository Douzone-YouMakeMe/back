package com.ymm.back.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter@Setter@ToString
public class JwtDTO implements Serializable {

    private String email;

    private String password;


}
