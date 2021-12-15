package com.nttdata.example.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "categoria")
public class Category {

    @Id
    @Column(name = "categoria_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoriaId;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Question> preguntas = new ArrayList<>();

    public Integer getCategoriaId() { return categoriaId; }

    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Question> getPreguntas() { return preguntas; }

    public void setPreguntas(List<Question> preguntas) { this.preguntas = preguntas; }

    public void agregarPregunta(Question pregunta) { this.preguntas.add(pregunta); }
}
