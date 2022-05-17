package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import converters.ConversorEntityDto;
import dao.ReservasDao;
import dtos.ReservaDto;
import model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {

	ReservasDao reservasDao;
	
	RestTemplate template;
	String urlVuelos = "http://servicio-vuelos/vuelos";
	
	@Autowired
	ConversorEntityDto conversor;
	
	public ReservasServiceImpl(@Autowired ReservasDao reservasDao, @Autowired RestTemplate template) {
		super();
		this.reservasDao = reservasDao;
		this.template = template;
	}

	@Override
	public boolean reservar(ReservaDto reservaDto) {
//		HttpEntity<String> requestEntity = new HttpEntity<String>("");
		ResponseEntity<String> response = template.exchange(urlVuelos + "/Vuelo/" + reservaDto.getVuelo() + "/" + reservaDto.getPersonas(),
															HttpMethod.PUT, null, String.class);
		if (response.getBody().equals("true")) {
			reservasDao.save(conversor.dtoToReserva(reservaDto));
			return true;
		}
		return false;
	}

	@Override
	public List<Reserva> reservas() {
		return reservasDao.findAll();
	}
}
