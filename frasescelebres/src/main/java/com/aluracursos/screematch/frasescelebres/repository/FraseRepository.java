package com.aluracursos.screematch.frasescelebres.repository;

import com.aluracursos.screematch.frasescelebres.dto.FraseDTO;
import com.aluracursos.screematch.frasescelebres.model.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FraseRepository extends JpaRepository<Frase,Long> {
    //@Query("Select f From Frase f Order By function('RANDOM') Limit 1")
    @Query("Select f From Frase f Order By RANDOM() Limit 1")
    public Frase obtenerFraseAleatoria();
}
