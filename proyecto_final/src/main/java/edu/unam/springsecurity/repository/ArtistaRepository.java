package edu.unam.springsecurity.repository;


import edu.unam.springsecurity.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista,String> {
}
