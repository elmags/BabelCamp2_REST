package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor	
@Setter	
@Getter
public class Pais {
	@JsonProperty(value = "name")
	private String nombre;
	private String capital;
	@JsonProperty(value = "region")
	private String continente;
	@JsonProperty(value = "population")
	private long poblacion;
	@JsonProperty(value = "flag")
	private String bandera;
}
