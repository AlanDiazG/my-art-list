package edu.unam.springsecurity.repository;


import edu.unam.springsecurity.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,String> {
}
