package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductosDao;
import model.Producto;

@Service
public class ProductosServiceImpl implements ProductosService {

	ProductosDao productosDao;
	
	public ProductosServiceImpl(@Autowired ProductosDao productosDao) {
		super();
		this.productosDao = productosDao;
	}

	@Override
	public List<Producto> productos() {
		return productosDao.findAll();
	}

	@Override
	public boolean actualizarStock(int codigoProducto, int unidades) {
		Producto producto = buscarProducto(codigoProducto);
		if(producto != null && producto.getStock() >= unidades) {
			producto.setStock(producto.getStock() - unidades);
			productosDao.save(producto);
			return true;
		}
		return false;
	}

	@Override
	public double precioProducto(int codigoProducto) {
		Producto producto = buscarProducto(codigoProducto);
		if (producto != null) {
			return producto.getPrecioUnitario();
		}
		return -1;
	}
	
	private Producto buscarProducto(int codigoProducto) {
		Optional<Producto> producto = productosDao.findById(codigoProducto);
		return producto.isEmpty() ? null : producto.get();
	}
}
