package service;

import java.util.List;

import model.Producto;

public interface ProductosService {
	List<Producto> productos();
	boolean actualizarStock(int codigoProducto, int unidades);
	double precioProducto(int codigoProducto);
}
