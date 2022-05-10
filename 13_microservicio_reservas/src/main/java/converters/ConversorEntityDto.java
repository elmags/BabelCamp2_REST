package converters;

import dtos.ReservaDto;
import model.Reserva;

public interface ConversorEntityDto {
	Reserva dtoToReserva(ReservaDto reservaDto);
}
