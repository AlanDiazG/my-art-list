package edu.unam.springsecurity.entities;


import edu.unam.springsecurity.validation.NoEspacioNoVacio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="productos")

public class Producto {
    @Id
    @Column(name = "clave_producto")
    private String clave;
    @NoEspacioNoVacio(message = "El nombre no debe estar vacío y no debe comenzar con un espacio")
    private String nombre;
    private Float precio;
    @NoEspacioNoVacio(message = "La descripcion no debe esta vacia y no debe comenzar con un espacio")
    private String descripcion;
    private Integer stock;

}
