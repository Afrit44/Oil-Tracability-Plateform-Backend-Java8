package com.Oil4Med.Oil4Med.DTO.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String email;

    private String password;


}
