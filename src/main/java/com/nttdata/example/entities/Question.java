package com.nttdata.example.entities;
import javax.persistence.*;
import java.util.*;

@Entity
@Table (name = "pregunta")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pregunta_id")
    private Integer preguntaId;

    private String enunciado;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    private Category categoria;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Response> opciones = new ArrayList<>();

    public Integer getPreguntaId() { return preguntaId; }

    public void setPreguntaId(Integer preguntaId) { this.preguntaId = preguntaId; }

    public String getEnunciado() { return enunciado; }

    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public Category getCategoria() { return categoria; }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
        this.categoria.agregarPregunta(this);
    }

    public List<Response> getOpciones() { return opciones; }

    public void setOpciones(List<Response> opciones) { this.opciones = opciones; }

    public void agregarRespuesta(Response respuesta){ this.opciones.add(respuesta); }
}
