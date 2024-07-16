package com.alura.Challenge_ForoHub.dto;

import com.alura.Challenge_ForoHub.model.Curso;
import com.alura.Challenge_ForoHub.model.Topico;

public record DatosListaTopico(Long id, String titulo, String mensaje, Curso curso) {
    public DatosListaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}
