package com.openwebinars.springrest.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openwebinars.springrest.crud.model.ErrorRest;

import com.openwebinars.springrest.crud.model.Puntuacion;
import com.openwebinars.springrest.crud.repo.PuntuacionRepository;



@RestController
@RequestMapping("/api")
public class PuntuacionController {

	@Autowired
	PuntuacionRepository puntuacionRepository;

	@GetMapping("/puntuacion")
	public List<Puntuacion> list() {
		List<Puntuacion> result = puntuacionRepository.findAll();
		if (result != null)
			return result;
		else
			throw new PuntuacionNotFoundException();
	}

	@GetMapping("/puntuacion/{id}")
	public Puntuacion getPuntuacion(@PathVariable Long id) {
		Puntuacion result = puntuacionRepository.findOne(id);
		if (result != null)
			return result;
		else
			throw new PuntuacionNotFoundException(id);
	}
	
	@GetMapping("/usuario/{id_usuario}/pelicula/{id_pelicula}")
	public Puntuacion getPuntuacionUsuarioPelicula(@PathVariable Long id_usuario, @PathVariable Long id_pelicula) {
		Puntuacion result = puntuacionRepository.findByUserIdAndMovieId(id_usuario, id_pelicula);
		if (result != null)
			return result;
		else
			throw new PuntuacionNotFoundException();
	}
		
	@PostMapping("/usuario/{id_usuario}/pelicula/{id_pelicula}")
	public ResponseEntity<?> createPuntuacion(@PathVariable Long id_usuario, @PathVariable Long id_pelicula, RequestEntity<Puntuacion> reqPuntuacion) {
		
		if (reqPuntuacion.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos de la puntuacion a dar de alta."),
					HttpStatus.BAD_REQUEST);
		}
		
		Puntuacion puntuacion = reqPuntuacion.getBody();

		if (puntuacionRepository.findByUserIdAndMovieId(id_usuario, id_pelicula) != null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La puntuacion con ID " + puntuacion.getId() + " ya existe"),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Puntuacion>(puntuacionRepository.save(puntuacion), HttpStatus.CREATED);
		}
	}

	@PutMapping("/puntuacion/{id}")
	public ResponseEntity<?> updatePuntuacion(@PathVariable Long id, RequestEntity<Puntuacion> reqPuntuacion) {
		
		if (reqPuntuacion.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos de la puntuacion a modificar"),
					HttpStatus.BAD_REQUEST);
		}
		
		if (puntuacionRepository.findOne(id) != null) {
			Puntuacion puntuacion = reqPuntuacion.getBody();
			Puntuacion aActualizar = new Puntuacion(id, puntuacion.getUserId(), puntuacion.getMovieId(),puntuacion.getScore(), puntuacion.getDate());
			return new ResponseEntity<Puntuacion>(puntuacionRepository.save(aActualizar), HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La puntuacion a modificar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/puntuacion/{id}")
	public ResponseEntity<?> deletePuntuacion(@PathVariable Long id) {
		Puntuacion aBorrar = puntuacionRepository.findOne(id);
		if (aBorrar != null) {
			puntuacionRepository.delete(aBorrar);
			return new ResponseEntity<Puntuacion>(aBorrar, HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La puntuacion a borrar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class PuntuacionNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 7295910574475009536L;

		public PuntuacionNotFoundException() {
			super("No existe ninguna puntuacion");
		}

		public PuntuacionNotFoundException(Long id) {
			super(String.format("No existe ninguna puntuacion con el ID = %d", id));
		}

	}

}
