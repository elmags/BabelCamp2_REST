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
import model.Hotel;
import service.HotelesService;

@CrossOrigin("*")
@RestController
public class HotelesController {

	@Autowired
	HotelesService hService;
	
	@ApiOperation(value = "Devuelve una lista con los datos de los hoteles disponibles")
	@GetMapping(value = "HotelesDisponibles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> hotelesDisponibles() {
		return hService.hotelesDisponibles();
	}
	
	@ApiOperation(value = "Devuelve los datos del hotel que tiene el nombre que recibe en la URL")
	@GetMapping(value = "Hotel/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hotel buscarHotel(@ApiParam(value = "Nombre del hotel que se quiere encontrar") @PathVariable("nombre") String nombre) {
		return hService.buscarHotel(nombre);
	}
}
