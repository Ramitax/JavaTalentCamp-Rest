package com.nttdata.example.controller;

import java.util.List;

import com.nttdata.example.entities.Category;
import com.nttdata.example.models.response.GenericResponse;
import com.nttdata.example.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Category>> traerCategorias() {
		return ResponseEntity.ok(categoriaService.traerCategorias());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> traerCategoriaPorId(@PathVariable Integer id) {
		GenericResponse respuesta = new GenericResponse();
		if (categoriaService.existePorId(id)) {
			return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
		} else {
			respuesta.id = id;
			respuesta.isOk = false;
			respuesta.message = "La categoria no existe";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@PostMapping
	public ResponseEntity<?> crearCategoria(@RequestBody Category categoria) {
		GenericResponse respuesta = new GenericResponse();
		if (categoriaService.crearCategoria(categoria)) {
			respuesta.id = categoria.getCategoriaId();
			respuesta.isOk = true;
			respuesta.message = "Categoria creada con exito";
			return ResponseEntity.ok(respuesta);
		} else {
			respuesta.isOk = false;
			respuesta.message = "Esta categoria ya esta creada";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCategoriaPorId(@PathVariable Integer id) {
		GenericResponse respuesta = new GenericResponse();
		if (categoriaService.eliminarCategoriaPorId(id)) {
			respuesta.isOk = true;
			respuesta.message = "La categoria fue eliminada";
			return ResponseEntity.ok(respuesta);
		} else {
			respuesta.isOk = false;
			respuesta.message = "Ocurrio un error al intentar ejecutar la solicitud";
			return ResponseEntity.badRequest().body(respuesta);
		}
	}
}
