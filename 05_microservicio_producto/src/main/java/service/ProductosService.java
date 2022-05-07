package service;

import java.util.List;

import model.Producto;

public interface ProductosService {
	void altaProducto(Producto producto);
	boolean eliminarProducto(int idProducto);
	List<Producto> productos();
	List<Producto> productos(String seccion);
}
