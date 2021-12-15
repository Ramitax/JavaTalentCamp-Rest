package com.nttdata.example.repository;

import com.nttdata.example.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Category, Integer> {

	Category findByNombre(String nombre);

	boolean existsByNombre(String nombre);

	Category findById(int id);
}
