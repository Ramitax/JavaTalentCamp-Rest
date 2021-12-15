package com.nttdata.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.example.entities.*;
import com.nttdata.example.repository.*;

@Service
public class PreguntaService {

	@Autowired
	PreguntaRepository preguntaRepository;

	@Autowired
	CategoriaService categoriaService;

	public List<Question> traerPreguntas() {
		return preguntaRepository.findAll();
	}

	public Question buscarPreguntaPorId(Integer preguntaId) {
		Optional<Question> resultado = preguntaRepository.findById(preguntaId);
		if (resultado.isPresent()) {
			return resultado.get();
		}
		return null;
	}

	public Question crearPregunta(String enunciado, Integer categoriaId, List<Response> opciones) {
		if (!existePreguntaPorPregunta(enunciado)) {
			Question pregunta = new Question();
			pregunta.setEnunciado(enunciado);
			Category categoria = categoriaService.buscarCategoriaPorId(categoriaId);
			pregunta.setCategoria(categoria);
			for (Response respuesta : opciones) {
				respuesta.setPregunta(pregunta);
			}
			preguntaRepository.save(pregunta);
			return pregunta;
		}
		return null;
	}

	public boolean existePorId(int id) {
		Question pregunta = preguntaRepository.findById(id);
		return pregunta != null;
	}

	public boolean existePreguntaPorPregunta(String enunciado) {
		Question pregunta = preguntaRepository.findByEnunciado(enunciado);
		return pregunta != null;
	}

}
