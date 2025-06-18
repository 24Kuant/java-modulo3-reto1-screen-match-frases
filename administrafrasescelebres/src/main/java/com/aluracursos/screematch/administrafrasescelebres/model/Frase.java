package com.aluracursos.screematch.administrafrasescelebres.model;

import jakarta.persistence.*;

@Entity
@Table(name = "frases")
public class Frase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String poster;
    private String fraseCelebre;
    private String personaje;

    public Frase() { //es el constructor predeterminado

    }

    public Frase(DatosSerie datosSerie, String fraseCelebre, String personaje) {
        this.titulo = datosSerie.titulo();
        this.poster = datosSerie.poster();
        this.fraseCelebre = fraseCelebre;
        this.personaje = personaje;
    }

    //Setters y Getters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFraseCelebre() {
        return fraseCelebre;
    }

    public void setFraseCelebre(String fraseCelebre) {
        this.fraseCelebre = fraseCelebre;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    @Override
    public String toString() {
        return  ", titulo='" + titulo + '\'' +
                ", poster='" + poster + '\'' +
                ", fraseCelebre='" + fraseCelebre + '\'' +
                ", personaje='" + personaje + '\'';
    }
}
