package com.openwebinars.springrest.crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openwebinars.springrest.crud.model.Puntuacion;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long>{

	Puntuacion findByUserIdAndMovieId(Long id_usuario, Long id_pelicula);

}
