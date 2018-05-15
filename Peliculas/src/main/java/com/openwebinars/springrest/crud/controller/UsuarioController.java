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

import com.openwebinars.springrest.crud.model.Usuario;
import com.openwebinars.springrest.crud.model.ErrorRest;
import com.openwebinars.springrest.crud.repo.UsuarioRepository;



@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuario")
	public List<Usuario> list() {
		List<Usuario> result = usuarioRepository.findAll();
		if (result != null)
			return result;
		else
			throw new UsuarioNotFoundException();
	}

	@GetMapping("/usuario/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		Usuario result = usuarioRepository.findOne(id);
		if (result != null)
			return result;
		else
			throw new UsuarioNotFoundException(id);
	}
		
	@PostMapping("/usuario")
	public ResponseEntity<?> createUsuario(RequestEntity<Usuario> reqUsuario) {
		
		if (reqUsuario.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos del usuario a dar de alta"),
					HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = reqUsuario.getBody();

		if (usuarioRepository.findOne(usuario.getId()) != null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("El usuario con ID " + usuario.getId() + " ya existe"),
					HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
		}
	}

	@PutMapping("/ususario/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable Long id, RequestEntity<Usuario> reqUsuario) {
		
		if (reqUsuario.getBody() == null) {
			return new ResponseEntity<ErrorRest>(new ErrorRest("Formato de petición incorrecto. Debe enviar los datos del usuario a modificar"),
					HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioRepository.findOne(id) != null) {
			Usuario usuario = reqUsuario.getBody();
			Usuario aActualizar = new Usuario(id, usuario.getNombre(), usuario.getClave());
			return new ResponseEntity<Usuario>(usuarioRepository.save(aActualizar), HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("El usuario a modificar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
		
		Usuario aBorrar = usuarioRepository.findOne(id);
		if (aBorrar != null) {
			usuarioRepository.delete(aBorrar);
			return new ResponseEntity<Usuario>(aBorrar, HttpStatus.OK);
		} else {
			return new ResponseEntity<ErrorRest>(new ErrorRest("El usuario a borrar no existe"),
					HttpStatus.NOT_FOUND);
		}
		
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class UsuarioNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 7295910574475009536L;

		public UsuarioNotFoundException() {
			super("No existe ningún usuario");
		}

		public UsuarioNotFoundException(Long id) {
			super(String.format("No existe ningún usario con el ID = %d", id));
		}

	}

}
