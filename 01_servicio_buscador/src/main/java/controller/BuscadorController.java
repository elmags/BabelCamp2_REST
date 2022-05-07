package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Pagina;
import service.BuscadorService;

@RestController
public class BuscadorController {
	
	@Autowired
	BuscadorService bService;
	
	@GetMapping(value = "Paginas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina> paginas() {
		return bService.paginas();
	}
	
//	@GetMapping(value = "Buscador", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<Pagina> buscador(@RequestParam("tematica") String tematica) {
//		return bService.buscar(tematica);
//	}
	
	@GetMapping(value = "Paginas/{tematica}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pagina> buscador(@PathVariable("tematica") String tematica) {
		return bService.buscar(tematica);
	}
	
	@PostMapping(value = "Pagina", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void alta(@RequestBody Pagina pagina) {
		bService.alta(pagina);
	}
	
	@DeleteMapping(value = "Eliminar")
	public void eliminar(@RequestParam("tematica") String tematica) {
		bService.eliminar(tematica);
	}
	
	@PutMapping(value = "Actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pagina actualizar(@RequestBody Pagina pagina) {
		return bService.actualizar(pagina);
	}
	
	@PutMapping(value = "ActualizarTematica")
	public void actualizarTematica(@RequestParam("direccion") String direccion, @RequestParam("tematica") String tematica) {
		bService.actualizarTematica(direccion, tematica);
	}
}
