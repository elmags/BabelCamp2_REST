package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.ReservasDao;
import model.Reserva;
import model.ReservaAux;

@Service
public class ReservasServiceImpl implements ReservasService {

	ReservasDao reservasDao;
	
	RestTemplate template;
	String urlVuelos = "http://localhost:9000/vuelos";
	
	public ReservasServiceImpl(@Autowired ReservasDao reservasDao, @Autowired RestTemplate template) {
		super();
		this.reservasDao = reservasDao;
		this.template = template;
	}

	@Override
	public boolean reservar(ReservaAux reservaAux) {
		HttpEntity<String> requestEntity = new HttpEntity<String>("");
		ResponseEntity<String> response = template.exchange(urlVuelos + "/Vuelo/" + reservaAux.getVuelo() + "/" + reservaAux.getPersonas(), HttpMethod.PUT, requestEntity, String.class);
		
		if (response.getBody().equals("true")) {
			reservasDao.save(new Reserva(reservaAux.getIdreserva(),
										 reservaAux.getNombre(),
										 reservaAux.getDni(),
										 reservaAux.getHotel(),
										 reservaAux.getVuelo()));
			return true;
		}
		return false;
	}

	@Override
	public List<Reserva> reservas() {
		return reservasDao.findAll();
	}

	
}
