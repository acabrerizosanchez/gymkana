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
			throw new EmpleadoNotFoundException();
	}

	@GetMapping("/pelicula/{id}")
	public Pelicula getEmpleado(@PathVariable Long id) {
		Pelicula result = peliculaRepository.findOne(id);
		if (result != null)
			return result;
		else
			throw new EmpleadoNotFoundException(id);
	}
		
	@PostMapping("/empleado")
	public ResponseEntity<?> createEmpleado(RequestEntity<Pelicula> reqEmpleado) {
		
		if (reqEmpleado.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos del empleado a dar de alta"),
					HttpStatus.BAD_REQUEST);
		}
		
		Pelicula pelicula = reqEmpleado.getBody();

		if (peliculaRepository.findOne(pelicula.getId()) != null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("El empleado con ID " + pelicula.getId() + " ya existe"),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Pelicula>(peliculaRepository.save(pelicula), HttpStatus.CREATED);
		}
	}

	@PutMapping("/empleado/{id}")
	public ResponseEntity<?> updateEmpleado(@PathVariable Long id, RequestEntity<Pelicula> reqEmpleado) {
		
		if (reqEmpleado.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos del empleado a modificar"),
					HttpStatus.BAD_REQUEST);
		}
		
		if (peliculaRepository.findOne(id) != null) {
			Pelicula empleado = reqEmpleado.getBody();
			Pelicula aActualizar = new Pelicula(id, empleado.getNombre(), empleado.getFechaEstreno());
			return new ResponseEntity<Pelicula>(peliculaRepository.save(aActualizar), HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("La pelicula a modificar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/pelicula/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
		
		Pelicula aBorrar = peliculaRepository.findOne(id);
		if (aBorrar != null) {
			peliculaRepository.delete(aBorrar);
			return new ResponseEntity<Pelicula>(aBorrar, HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("la pelicula a borrar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class EmpleadoNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 7295910574475009536L;

		public EmpleadoNotFoundException() {
			super("No existe ningún empleado");
		}

		public EmpleadoNotFoundException(Long id) {
			super(String.format("No existe ningún empleado con el ID = %d", id));
		}

	}

}
