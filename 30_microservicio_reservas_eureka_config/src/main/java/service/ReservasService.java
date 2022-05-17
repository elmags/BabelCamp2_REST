package service;

import java.util.List;

import dtos.ReservaDto;
import model.Reserva;

public interface ReservasService {
	boolean reservar(ReservaDto reservaAux);
	List<Reserva> reservas();
}
