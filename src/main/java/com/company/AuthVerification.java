package com.company;

import lombok.Data;

@Data
public class AuthVerification {
    private String email;
    private Integer code;
}
