package com.aluracursos.screematch.administrafrasescelebres.principal;

import com.aluracursos.screematch.administrafrasescelebres.model.DatosSerie;
import com.aluracursos.screematch.administrafrasescelebres.model.Frase;
import com.aluracursos.screematch.administrafrasescelebres.repository.FraseRepository;
import com.aluracursos.screematch.administrafrasescelebres.service.ConsumoAPI;
import com.aluracursos.screematch.administrafrasescelebres.service.ConvierteDatos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {
    @Value("${key.screen.match.omdb}")  //Leyendo directo de una variable de entorno. //si no agrega una de estas anotaciones (@Component, @Service, @Controller, etc), @Value, siempre regresara null
    private String apiKey;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct - API Key: " + this.apiKey);
    }

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";

    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosSerie> datosSerie = new ArrayList<>();
    private FraseRepository repositorio;


    public Principal(FraseRepository repository) {
        this.repositorio = repository;
    }

    private String getApiKey() {
        String API_KEY = "&apikey=" + this.apiKey;  // "&apikey=xdf-f209486631s2e_;10";  //variable de entorno: JAVA_SCREEN_MATCH_OMDB_APIKEY
        return API_KEY;
    }

    public void muestraElMenu() {
        System.out.println("apiKey=" + this.apiKey + "; API_KEY=" + this.getApiKey());
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar series 
                    2 - Consultar las frases celebres
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSerieWebRepository();  //con repository. para guardar la info en la BD de postgres.
                    break;
                case 2:
                    buscarTodasLasFrasesCelebres();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosSerie getDatosSerie() {
        System.out.println("Escribe el nombre de la pelicula/serie que deseas buscar");
        var nombreSerie = teclado.nextLine();  //probar con: vikings / friends

        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + this.getApiKey());
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        return datos;
    }

    private void buscarSerieWebRepository() {
        DatosSerie datosSerie = getDatosSerie();
        if (datosSerie == null) {
            System.out.println("La pelicula/serie No existe.");
            return;
        }

        System.out.println("Escribe la Frase celebre que quieres agregar de la pelicula/serie");
        var frase = teclado.nextLine();

        if (frase == null) {
            System.out.println("La Frase celebre no puede ser vacia.");
            return;
        }

        System.out.println("Escribe el nombre del personaje que dijo la frase celebre");
        var personaje = teclado.nextLine();

        if (personaje == null) {
            System.out.println("El personaje que dijo la frase celebre no puede ser vacio.");
            return;
        }

        Frase fraseSerie = new Frase(datosSerie, frase, personaje);
        repositorio.save(fraseSerie);  //salva de forma automatica los datos de la Frase celebre de la serie, en la tabla frases de la BD alura_series de postgres
        System.out.println(fraseSerie);
    }

    private void buscarTodasLasFrasesCelebres() {
        List<Frase> frases = repositorio.findAll();  //recupera todos los datos de la BD alura_series tabla frases

        frases.stream()
                .sorted(Comparator.comparing(Frase::getTitulo))
                .forEach(System.out::println);
    }
}
