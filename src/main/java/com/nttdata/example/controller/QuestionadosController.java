package com.nttdata.example.controller;

import com.nttdata.example.entities.Question;
import com.nttdata.example.models.request.RespuestaAVerificar;
import com.nttdata.example.models.response.RespuestaVerificada;
import com.nttdata.example.service.QuestionadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questionados")
public class QuestionadosController {

	@Autowired
	QuestionadosService questionadosService;

	@GetMapping("/next")
	public ResponseEntity<Question> traerPreguntaRandom() {
		Question proximaPregunta = questionadosService.traerPreguntaRandom();
		return ResponseEntity.ok(proximaPregunta);
	}

	@PostMapping("/verificaciones")
	public ResponseEntity<RespuestaVerificada> verificarRespuesta(
			@RequestBody RespuestaAVerificar respuestaAVerificar) {
		RespuestaVerificada respuestaVerificada = new RespuestaVerificada();
		if (questionadosService.verificarRespuesta(respuestaAVerificar.preguntaId, respuestaAVerificar.respuestaId)) {
			respuestaVerificada.esCorrecta = true;
		} else {
			respuestaVerificada.esCorrecta = false;
		}
		return ResponseEntity.ok(respuestaVerificada);
	}

}
