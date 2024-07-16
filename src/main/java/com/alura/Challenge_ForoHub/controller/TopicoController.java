package com.alura.Challenge_ForoHub.controller;

import com.alura.Challenge_ForoHub.dto.DatosActualizarTopico;
import com.alura.Challenge_ForoHub.dto.DatosDetalleTopico;
import com.alura.Challenge_ForoHub.dto.DatosListaTopico;
import com.alura.Challenge_ForoHub.dto.DatosRegistroTopico;
import com.alura.Challenge_ForoHub.model.Topico;
import com.alura.Challenge_ForoHub.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        var topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> datosListaTopicos(@PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {
        var page = topicoRepository.findAll(pageable).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarInformacion(datosActualizarTopico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }
}