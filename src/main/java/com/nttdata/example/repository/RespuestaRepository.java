package com.nttdata.example.repository;

import com.nttdata.example.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Response, Integer> {

}
