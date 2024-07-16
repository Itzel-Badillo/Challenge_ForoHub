package com.alura.Challenge_ForoHub.dto;

import com.alura.Challenge_ForoHub.model.Curso;
import com.alura.Challenge_ForoHub.model.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(String titulo, String mensaje, LocalDateTime fechaCreacion, Curso curso) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getCurso());
    }
}
