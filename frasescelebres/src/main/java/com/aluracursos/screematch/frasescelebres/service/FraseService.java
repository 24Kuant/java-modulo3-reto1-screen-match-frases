package com.aluracursos.screematch.frasescelebres.service;

import com.aluracursos.screematch.frasescelebres.dto.FraseDTO;
import com.aluracursos.screematch.frasescelebres.model.Frase;
import com.aluracursos.screematch.frasescelebres.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FraseService {
    @Autowired
    private FraseRepository repositorio;

    public FraseDTO obtenFraseCelebre() {
        return this.obtenDatos(repositorio.obtenerFraseAleatoria());
    }

    private FraseDTO obtenDatos(Frase frase) {
        return new FraseDTO( frase.getTitulo(), frase.getPoster(),
                frase.getFraseCelebre(), frase.getPersonaje() );
    }
}
