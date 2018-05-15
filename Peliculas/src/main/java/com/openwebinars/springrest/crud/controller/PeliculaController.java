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

import com.openwebinars.springrest.crud.model.Pelicula;
import com.openwebinars.springrest.crud.model.ErrorRest;
import com.openwebinars.springrest.crud.repo.PeliculaRepository;



@RestController
@RequestMapping("/api")
public class PeliculaController {

	@Autowired
	PeliculaRepository peliculaRepository;

	@GetMapping("/pelicula")
	public List<Pelicula> list() {
		List<Pelicula> result = peliculaRepository.findAll();
		if (result != null)
			return result;
		else
			throw new PeliculaNotFoundException();
	}

	@GetMapping("/pelicula/{id}")
	public Pelicula getPelicula(@PathVariable Long id) {
		Pelicula result = peliculaRepository.findOne(id);
		if (result != null)
			return result;
		else
			throw new PeliculaNotFoundException(id);
	}
		
	@PostMapping("/pelicula")
	public ResponseEntity<?> createPelicula(RequestEntity<Pelicula> reqPelicula) {
		
		if (reqPelicula.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos de la pelicula a dar de alta"),
					HttpStatus.BAD_REQUEST);
		}
		
		Pelicula pelicula = reqPelicula.getBody();

		if (peliculaRepository.findOne(pelicula.getId()) != null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La pelicula con ID " + pelicula.getId() + " ya existe"),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Pelicula>(peliculaRepository.save(pelicula), HttpStatus.CREATED);
		}
	}

	@PutMapping("/pelicula/{id}")
	public ResponseEntity<?> updatePelicula(@PathVariable Long id, RequestEntity<Pelicula> reqPelicula) {
		
		if (reqPelicula.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos de la pelicula a modificar"),
					HttpStatus.BAD_REQUEST);
		}
		
		if (peliculaRepository.findOne(id) != null) {
			Pelicula pelicula = reqPelicula.getBody();
			Pelicula aActualizar = new Pelicula(id, pelicula.getNombre(), pelicula.getFechaEstreno());
			return new ResponseEntity<Pelicula>(peliculaRepository.save(aActualizar), HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La pelicula a modificar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/pelicula/{id}")
	public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
		
		Pelicula aBorrar = peliculaRepository.findOne(id);
		if (aBorrar != null) {
			peliculaRepository.delete(aBorrar);
			return new ResponseEntity<Pelicula>(aBorrar, HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La pelicula a borrar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class PeliculaNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 7295910574475009536L;

		public PeliculaNotFoundException() {
			super("No existe ninguna película");
		}

		public PeliculaNotFoundException(Long id) {
			super(String.format("No existe ninguna película con el ID = %d", id));
		}

	}

}
