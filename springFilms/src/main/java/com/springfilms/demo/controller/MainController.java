package com.springfilms.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador endPoint "/" Muestra en el navegador una frase simple
 * 
 * @author acosanchez
 *
 */
@Controller
public class MainController {

	@RequestMapping("/")
	@ResponseBody
	public String sayHelloWorld() {
		return "Starter niAmateur Filmafinity";
	}
}
