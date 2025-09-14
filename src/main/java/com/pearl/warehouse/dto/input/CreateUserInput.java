package com.pearl.warehouse.dto.input;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInput {
    public String username;
    public String password;
    public String email;
    public Set<String> roles;
}
