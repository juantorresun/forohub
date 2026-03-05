package com.forohub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String nombre;
    private String correoElectronico;
    private String contrasena;
}