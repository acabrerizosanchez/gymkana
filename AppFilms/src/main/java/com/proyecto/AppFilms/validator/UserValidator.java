package com.proyecto.AppFilms.validator;



import java.text.SimpleDateFormat;

import org.springframework.stereotype.Repository;

import com.proyecto.AppFilms.model.User;

@Repository
public class UserValidator {
	
	public boolean validarFecha(String fecha) {

		
    	int d = Integer.parseInt(fecha.substring(8, 10));
		int m = Integer.parseInt(fecha.substring(5, 7));
		int a = Integer.parseInt(fecha.substring(0, 4));

		

        if(a > 2020 || a < 0 ||m < 1 || m > 12 || d < 1 || d > 31) {
        	return false;
        }else {
        return true;
        
        }

	}
	
	public boolean validate(User user) {
	

		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = formatoFecha.format(user.getBirthDate());
		
		
		if(validarFecha(fecha) == false) {
			user.setErrorMessage("El formato de la fecha es incorrecto");
			return false;
		}
			return  true;
	}
		
	
	



	

}

