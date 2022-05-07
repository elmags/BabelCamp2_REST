package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Producto;
import service.ProductosService;

@RestController
public class ProductosController {

	@Autowired
	ProductosService pService;
	
	@PostMapping(value = "Producto", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void altaProducto(@RequestBody Producto producto) {
		pService.altaProducto(producto);
	}
	
	@DeleteMapping(value = "Producto/{idProducto}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String eliminarProducto(@PathVariable("idProducto") int idProducto) {
		return pService.eliminarProducto(idProducto) + "";
	}
	
	@GetMapping(value = "Productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> productos() {
		return pService.productos();
	}
	
	@GetMapping(value = "Productos/{seccion}")
	public List<Producto> productos(@PathVariable("seccion") String seccion) {
		return pService.productos(seccion);
	}
}
