package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Reserva;
import model.ReservaAux;
import service.ReservasService;

@RestController
public class ReservasController {
	
	@Autowired
	ReservasService rService;
	
	@PostMapping(value = "Reserva", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean reservar(@RequestBody ReservaAux reservaAux) {
		return rService.reservar(reservaAux);
	}
	
	@GetMapping(value = "Reservas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Reserva> reservas() {
		return rService.reservas();
	}
}
