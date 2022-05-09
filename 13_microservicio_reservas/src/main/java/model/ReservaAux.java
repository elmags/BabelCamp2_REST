package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservaAux {
		private int idreserva;
		private String nombre;
		private String dni;
		private int hotel;
		private int vuelo;
		private int personas;
}
