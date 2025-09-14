package com.pearl.warehouse.dto.input;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
