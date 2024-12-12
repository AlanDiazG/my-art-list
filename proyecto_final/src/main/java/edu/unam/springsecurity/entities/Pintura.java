package edu.unam.springsecurity.entities;


import edu.unam.springsecurity.validation.NoEspacioNoVacio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pinturas")
public class Pintura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NoEspacioNoVacio(message = "El Titulo de la Obra no debe estar vacío y no debe comenzar con un espacio")
    private String tituloObra;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "anioCreacion")
    private LocalDate anioCreacion;
    @NoEspacioNoVacio(message = "La tecnica no debe estar vacío y no debe comenzar con un espacio")
    private String tecnica;
    @NoEspacioNoVacio(message = "Los detalles no debe estar vacío y no debe comenzar con un espacio")
    private String detalles;
    @ManyToOne
    @JoinColumn(name = "idArtista")
    @ToString.Exclude
    private Artista artista;

    /* TEST */
    @OneToMany(mappedBy = "pintura")
    @ToString.Exclude
    private List<PinturaEstudiada> pinturasEstudiadas = new ArrayList<>();
}