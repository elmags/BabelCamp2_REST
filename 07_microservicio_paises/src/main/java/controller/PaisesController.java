package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.Pais;
import service.PaisesService;

@CrossOrigin("*")
@RestController
public class PaisesController {
	
	@Autowired
	PaisesService pService;
	
	@ApiOperation(value = "Devuelve los nombres de los continentes")
	@GetMapping(value = "Continentes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes() {
		return pService.continentes();
	}
	
	@ApiOperation(value = "Devuelve una lista con los datos de los paises que pertenecen al continente recibido en la URL")
	@GetMapping(value = "Paises/{continente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> paisesPorContinente(@ApiParam("Nombre del contienente") @PathVariable("continente") String continente) {
		return pService.paisesPorContinente(continente);
	}

	@ApiOperation(value = "Devuelve la población total del continente recibido en la URL")
	@GetMapping(value = "Poblacion/{continente}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String poblacionContinente(@ApiParam("Nombre del contienente") @PathVariable("continente") String continente) {
		return pService.poblacionContinente(continente) + "";
	}
}
