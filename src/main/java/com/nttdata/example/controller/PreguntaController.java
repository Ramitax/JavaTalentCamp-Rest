package com.nttdata.example.controller;

import java.util.List;

import com.nttdata.example.entities.Question;
import com.nttdata.example.models.request.InfoPreguntaNueva;
import com.nttdata.example.models.response.GenericResponse;
import com.nttdata.example.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

	@Autowired
	PreguntaService service;

	@GetMapping
	public ResponseEntity<List<Question>> traerPreguntas() {
		return ResponseEntity.ok(service.traerPreguntas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPreguntaPorId(@PathVariable Integer id) {
		GenericResponse respuesta = new GenericResponse();
		if (service.existePorId(id)) {
			return ResponseEntity.ok(service.buscarPreguntaPorId(id));
		} else {
			respuesta.isOk = false;
			respuesta.message = "La pregunta no existe";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@PostMapping
	public ResponseEntity<?> crearPregunta(@RequestBody InfoPreguntaNueva preguntaNueva) {
		GenericResponse respuesta = new GenericResponse();
		Question pregunta = new Question();
		if (service.crearPregunta(preguntaNueva.enunciado, preguntaNueva.categoriaId, preguntaNueva.opciones) != null) {
			respuesta.id = pregunta.getPreguntaId();
			respuesta.isOk = true;
			respuesta.message = "La pregunta fue creada con exito";
			return ResponseEntity.ok(respuesta);
		} else {
			respuesta.isOk = false;
			respuesta.message = "La pregunta ya existe";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}
}
