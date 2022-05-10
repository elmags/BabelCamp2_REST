package converters;

import org.springframework.stereotype.Component;

import dtos.ReservaDto;
import model.Reserva;

@Component
public class ConversorEntityDtoImpl implements ConversorEntityDto {

	@Override
	public Reserva dtoToReserva(ReservaDto reservaDto) {
		return new Reserva(reservaDto.getIdreserva(), reservaDto.getNombre(), reservaDto.getDni(), reservaDto.getHotel(), reservaDto.getVuelo());
	}
}
