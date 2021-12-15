package com.nttdata.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.example.entities.*;
import com.nttdata.example.repository.*;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Category> traerCategorias() {
		return categoriaRepository.findAll();
	}

	public Category buscarCategoriaPorId(Integer categoriaId) {
		Optional<Category> resultado = categoriaRepository.findById(categoriaId);
		Category categoria = null;
		if (resultado.isPresent()) {
			categoria = resultado.get();
		}
		return categoria;
	}

	public boolean crearCategoria(Category categoria) {
		if (existePorNombre(categoria.getNombre())) {
			return false;
		}
		categoriaRepository.save(categoria);
		return true;
	}

	public boolean existePorId(int id) {
		Category categoria = categoriaRepository.findById(id);
		return categoria != null;
	}

	public boolean existePorNombre(String nombre) {
		Category categoria = categoriaRepository.findByNombre(nombre);
		return categoria != null;
	}

	public boolean eliminarCategoriaPorId(Integer id) {
		boolean res = false;
		if (existePorId(id)) {
			categoriaRepository.deleteById(id);
			res = (!existePorId(id));
		}
		return res;
	}

}
