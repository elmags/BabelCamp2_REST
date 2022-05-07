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
	public void altaProducto(Producto producto) {
		productosDao.save(producto);
	}

	@Override
	public boolean eliminarProducto(int idProducto) {
		if (buscarProducto(idProducto) != null) {
			productosDao.deleteById(idProducto);
			return true;
		}
		return false;
	}

	@Override
	public List<Producto> productos() {
		return productosDao.findAll();
	}

	@Override
	public List<Producto> productos(String seccion) {
		return productosDao.findBySeccion(seccion);
	}
	
	private Producto buscarProducto(int idProducto) {
		Optional<Producto> producto = productosDao.findById(idProducto);
		return producto.isEmpty() ? null : producto.get();
	}
}
