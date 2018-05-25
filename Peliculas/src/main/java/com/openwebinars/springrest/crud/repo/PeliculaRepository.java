package com.openwebinars.springrest.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openwebinars.springrest.crud.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{

}
