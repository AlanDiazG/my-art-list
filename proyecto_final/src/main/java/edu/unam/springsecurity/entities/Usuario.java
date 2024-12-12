package edu.unam.springsecurity.entities;



import edu.unam.springsecurity.auth.model.UserInfo;
import edu.unam.springsecurity.validation.NoEspacioNoVacio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NoEspacioNoVacio(message = "El correo no debe estar vacío y no debe comenzar con un espacio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @NoEspacioNoVacio(message = "El nombre no debe estar vacío y no debe comenzar con un espacio")
    private String nombre;

    @NoEspacioNoVacio(message = "La contrasena no debe estar vacío y no debe comenzar con un espacio")
    private String contrasena;

    @NoEspacioNoVacio(message = "La direccion no debe estar vacío y no debe comenzar con un espacio")
    private String direccion;

    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos")
    @NoEspacioNoVacio(message = "El telefono no debe estar vacío y no debe comenzar con un espacio")
    private String telefono;

    @NotNull(message = "El género es obligatorio")
    @ManyToOne
    @JoinColumn(name = "idGenero")
    @ToString.Exclude // Evita la recursión en toString()
    private Genero genero;

    @NotNull(message = "El género es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_carrera_profesional")
    @ToString.Exclude // Evita la recursión en toString()
    private CarreraProfesional carreraProfesional;

    @OneToMany(mappedBy = "usuario")
    private List<PinturaEstudiada> pinturaEstudiadas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Tarjeta> tarjetas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "id_sec_user", referencedColumnName = "use_id")
    private UserInfo secUser;
}
