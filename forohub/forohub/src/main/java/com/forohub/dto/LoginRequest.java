package com.forohub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String correoElectronico;
    private String contrasena;
}