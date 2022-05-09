package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Vuelo;
import service.VuelosService;

@RestController
public class VuelosController {

	@Autowired
	VuelosService vService;
	
	@GetMapping(value = "VuelosDisponibles/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Vuelo> vuelosDisponibles(@PathVariable("plazas") int plazas) {
		return vService.vuelosDisponibles(plazas);
	}
	
	@PutMapping(value = "Vuelo/{idVuelo}/{plazas}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String reservarVuelo(@PathVariable("idVuelo") int idVuelo, @PathVariable("plazas") int plazas) {
		return vService.reservarVuelo(idVuelo, plazas) + "";
	}
}
