package service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {

	RestTemplate template;
	String urlBase = "https://restcountries.com/v2/all";
	Pais[] paises;
	
	public PaisesServiceImpl(@Autowired RestTemplate template) {
		super();
		this.template = template;
	}

	@PostConstruct		// Hace que se ejecute el metodo solo una vez despues del contructor
	public void init() { paises = paises(); }
	
	@Override
	public List<String> continentes() {
		return Arrays.stream(paises).map(p -> p.getContinente()).distinct().collect(Collectors.toList());
	}

	@Override
	public List<Pais> paisesPorContinente(String continente) {
		return Arrays.stream(paises).filter(p -> p.getContinente().equals(continente)).collect(Collectors.toList());
	}

	@Override
	public long poblacionContinente(String continente) {
		long poblacion = 0;
		for(Pais pais : paises)
			if(pais.getContinente().equals(continente)) 
				poblacion += pais.getPoblacion();
		return poblacion;
	}
	
//	@Override
//	public long poblacionContinente(String continente) {
//		return Arrays.stream(paises()).filter(p -> p.getContinente().equals(continente))
//		.collect(Collectors.summingLong(p -> p.getPoblacion()));
//	}
	
//	@Override
//	public long poblacionContinente(String continente) {
//		return Arrays.stream(paises()).filter(p -> p.getContinente().equals(continente))
//		.mapToLong(p -> p.getPoblacion()).sum();
//	}
	
	private Pais[] paises() {
		return template.getForObject(urlBase, Pais[].class);
	}
}
