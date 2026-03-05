package com.forohub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoRequest {

    private String titulo;
    private String mensaje;
    private String status;
    private Long cursoId;
}