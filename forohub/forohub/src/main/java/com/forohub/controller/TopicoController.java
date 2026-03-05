package com.forohub.controller;

import com.forohub.dto.TopicoRequest;
import com.forohub.dto.TopicoUpdateRequest;
import com.forohub.entity.*;
import com.forohub.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Topico> listar() {
        return topicoRepository.findAll();
    }

    @PostMapping
    public Topico crear(
            @RequestBody TopicoRequest request,
            @AuthenticationPrincipal(expression = "username")
            String correo) {

        Usuario autor = usuarioRepository
                .findByCorreoElectronico(correo)
                .orElseThrow();

        Curso curso = cursoRepository
                .findById(request.getCursoId())
                .orElseThrow();

        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setStatus(request.getStatus());
        topico.setAutor(autor);
        topico.setCurso(curso);

        return topicoRepository.save(topico);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(
            @PathVariable Long id,
            @RequestBody TopicoUpdateRequest request) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setStatus(request.getStatus());

        topicoRepository.save(topico);

        return ResponseEntity.ok("Tópico actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topicoRepository.delete(topico);

        return ResponseEntity.ok("Tópico eliminado correctamente");
    }
}