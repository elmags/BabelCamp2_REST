package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VuelosDao;
import model.Vuelo;

@Service
public class VuelosServiceImpl implements VuelosService {

	VuelosDao vuelosDao;
	
	public VuelosServiceImpl(@Autowired VuelosDao vuelosDao) {
		super();
		this.vuelosDao = vuelosDao;
	}

	@Override
	public List<Vuelo> vuelosDisponibles(int plazas) {
		return vuelosDao.findAll().stream().filter(v -> (v.getPlazas() >= plazas))
			   .collect(Collectors.toList());
	}

	@Override
	public boolean reservarVuelo(int idVuelo, int plazas) {
		Vuelo vuelo = buscarVuelo(idVuelo);
		if (vuelo != null && vuelo.getPlazas() >= plazas) {
			vuelo.setPlazas(vuelo.getPlazas() - plazas);
			vuelosDao.save(vuelo);
			return true;
		}
		return false;
	}
	
	private Vuelo buscarVuelo(int idVuelo) {
		Optional<Vuelo> vuelo = vuelosDao.findById(idVuelo);
		return vuelo.isEmpty() ? null : vuelo.get();
	}
}
