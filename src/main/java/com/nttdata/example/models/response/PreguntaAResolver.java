package com.nttdata.example.models.response;

import java.util.*;

import com.nttdata.example.entities.Category;
import com.nttdata.example.entities.Question;
import com.nttdata.example.entities.Response;

public class PreguntaAResolver {

    public Integer preguntaId;
    public String enunciado;
    public Category categoria;
    public List<OpcionPregunta> opciones = new ArrayList<>();

    public static PreguntaAResolver convertirDesde(Question pregunta) {
        
        PreguntaAResolver preguntaAResolver = new PreguntaAResolver();

        preguntaAResolver.preguntaId = pregunta.getPreguntaId();
        preguntaAResolver.enunciado = pregunta.getEnunciado();
        preguntaAResolver.categoria = pregunta.getCategoria();
        
        preguntaAResolver.opciones = new ArrayList<>();

        for(Response respuesta : pregunta.getOpciones()) {

            OpcionPregunta opcion = new OpcionPregunta();

            opcion.respuestaId = respuesta.getRespuestaId();
            opcion.texto = respuesta.getTexto();

            preguntaAResolver.opciones.add(opcion);
        }

        return preguntaAResolver;
    }
    
}
