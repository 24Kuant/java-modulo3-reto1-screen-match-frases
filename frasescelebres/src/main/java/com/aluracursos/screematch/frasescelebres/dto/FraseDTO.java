package com.aluracursos.screematch.frasescelebres.dto;

import jakarta.persistence.*;


public record FraseDTO(
        String titulo,
        String poster,
        String frase,
        String personaje
) {

}
