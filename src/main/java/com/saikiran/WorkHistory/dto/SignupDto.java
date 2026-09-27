package com.saikiran.WorkHistory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupDto {
    private String name;
    private String number;
    private String password;
}
