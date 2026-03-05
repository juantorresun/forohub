package com.forohub.controller;

import com.forohub.dto.RespuestaRequest;
import com.forohub.dto.RespuestaUpdateRequest;
import com.forohub.entity.*;
import com.forohub.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/{topicoId}")
    public List<Respuesta> listarPorTopico(
            @PathVariable Long topicoId) {

        return respuestaRepository.findByTopicoId(topicoId);
    }

    @PostMapping
    public Respuesta crear(
            @RequestBody RespuestaRequest request,
            @AuthenticationPrincipal(expression = "username")
            String correo) {

        Usuario autor = usuarioRepository
                .findByCorreoElectronico(correo)
                .orElseThrow();

        Topico topico = topicoRepository
                .findById(request.getTopicoId())
                .orElseThrow();

        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(request.getMensaje());
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);

        return respuestaRepository.save(respuesta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRespuesta(
            @PathVariable Long id,
            @RequestBody RespuestaUpdateRequest request) {

        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        respuesta.setMensaje(request.getMensaje());
        respuesta.setSolucion(request.getSolucion());

        respuestaRepository.save(respuesta);

        return ResponseEntity.ok("Respuesta actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {

        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

        respuestaRepository.delete(respuesta);

        return ResponseEntity.ok("Respuesta eliminada correctamente");
    }

}