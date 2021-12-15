package com.nttdata.example.repository;

import com.nttdata.example.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Question, Integer> {

	Question findById(int id);

	Question findByEnunciado(String enunciado);
}
