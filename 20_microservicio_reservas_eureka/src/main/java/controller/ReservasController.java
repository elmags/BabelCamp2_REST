package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dtos.ReservaDto;
import model.Reserva;
import service.ReservasService;

@CrossOrigin("*")
@RestController
public class ReservasController {
	
	@Autowired
	ReservasService rService;
	
	@PostMapping(value = "Reserva", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean reservar(@RequestBody ReservaDto reservaDto) {
		return rService.reservar(reservaDto);
	}
	
	@GetMapping(value = "Reservas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Reserva> reservas() {
		return rService.reservas();
	}
}
