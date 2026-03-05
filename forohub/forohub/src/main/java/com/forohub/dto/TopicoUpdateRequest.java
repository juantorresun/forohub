package com.forohub.dto;

import lombok.Data;

@Data
public class TopicoUpdateRequest {
    private String titulo;
    private String mensaje;
    private String status;
}