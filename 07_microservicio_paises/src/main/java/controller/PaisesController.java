package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.PaisesService;

@CrossOrigin("*")
@RestController
public class PaisesController {
	
	@Autowired
	PaisesService pService;
	
	@GetMapping(value = "Continentes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes() {
		return pService.continentes();
	}
	
	@GetMapping(value = "Paises/{continente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> paisesPorContinente(@PathVariable("continente") String continente) {
		return pService.paisesPorContinente(continente);
	}

	@GetMapping(value = "Poblacion/{continente}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String poblacionContinente(@PathVariable("continente") String continente) {
		return pService.poblacionContinente(continente) + "";
	}
}
