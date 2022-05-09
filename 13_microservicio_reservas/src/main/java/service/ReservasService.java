package service;

import java.util.List;

import model.Reserva;
import model.ReservaAux;

public interface ReservasService {
	boolean reservar(ReservaAux reservaAux);
	List<Reserva> reservas();
}
