package com.alura.Challenge_ForoHub.dto;

import com.alura.Challenge_ForoHub.model.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank
                                  String titulo,
                                  @NotBlank
                                  String mensaje,
                                  @NotNull @Valid
                                  Curso curso) {
}