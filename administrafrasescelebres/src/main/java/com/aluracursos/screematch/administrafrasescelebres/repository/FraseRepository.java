package com.aluracursos.screematch.administrafrasescelebres.repository;

import com.aluracursos.screematch.administrafrasescelebres.model.Frase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FraseRepository extends JpaRepository<Frase,Long> {
    List<Frase> findAll();
}
