package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

//Esto es un JavaBean
public class Pagina {
	private String direccion;
	private String tematica;
	private String descripcion;
}
