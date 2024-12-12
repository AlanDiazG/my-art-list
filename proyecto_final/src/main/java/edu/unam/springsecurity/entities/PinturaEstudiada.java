package edu.unam.springsecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pinturaEstudiada")
public class PinturaEstudiada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idPintura")
    @ToString.Exclude
    private Pintura pintura;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @ToString.Exclude
    private Usuario usuario;

    private Date fechaEstudio;
    private String observaciones;


}
