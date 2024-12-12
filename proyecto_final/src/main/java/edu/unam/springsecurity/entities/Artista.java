package edu.unam.springsecurity.entities;


import edu.unam.springsecurity.validation.NoEspacioNoVacio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NoEspacioNoVacio(message = "El nombre no debe estar vacío y no debe comenzar con un espacio")
    private String nombre;
    @NoEspacioNoVacio(message = "La nacionalidad no debe estar vacío y no debe comenzar con un espacio")
    private String nacionalidad;

    @OneToMany(mappedBy = "artista")
    @ToString.Exclude
    private List<Pintura> pinturas = new ArrayList<>();
}
