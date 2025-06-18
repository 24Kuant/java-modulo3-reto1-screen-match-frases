package com.aluracursos.screematch.frasescelebres.controller;

import com.aluracursos.screematch.frasescelebres.dto.FraseDTO;
import com.aluracursos.screematch.frasescelebres.service.FraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FrasesCelebresController {
    @Autowired
    private FraseService servicio;

    @GetMapping("/series/frases")
    public FraseDTO testLiveReloading() {
        return servicio.obtenFraseCelebre();
    }

}
