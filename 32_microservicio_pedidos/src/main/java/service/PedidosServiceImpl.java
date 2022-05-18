package service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dao.PedidosDao;
import model.Pedido;

@Service
public class PedidosServiceImpl implements PedidosService {
	
	PedidosDao pedidosDao;
	
	RestTemplate template;
	String urlProductos = "http://servicio-productos/productos";

	
	public PedidosServiceImpl(@Autowired PedidosDao pedidosDao, RestTemplate template) {
		super();
		this.pedidosDao = pedidosDao;
		this.template = template;
	}

	@Override
	public boolean altaPedido(Pedido pedido) {
		ResponseEntity<String> responsePut = template.exchange(urlProductos + "/Producto/" + pedido.getCodigoProducto() + "/" + pedido.getUnidades(),
										  HttpMethod.PUT, null, String.class);
		ResponseEntity<Double> responseGet = template.exchange(urlProductos + "/Producto/" + pedido.getCodigoProducto(),
				  							 HttpMethod.GET, null, Double.class);
		if (responsePut.getBody().equals("true")) {
			pedido.setTotal(responseGet.getBody() * pedido.getUnidades());
			Long datetime = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(datetime);
			pedido.setFechaPedido(timestamp);
			pedidosDao.save(pedido);
			return true;
		}
		return false;
	}

	@Override
	public List<Pedido> pedidos() {
		return pedidosDao.findAll();
	}

}
