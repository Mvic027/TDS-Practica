package umu.tds.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import umu.tds.dominio.Gasto;

public class RepositorioGastosMemoria implements RepositorioGastos {
	private final Map<UUID, Gasto> gastos = new HashMap<>();
	@Override
	public boolean anadirGasto(Gasto gasto) {
		if(gasto==null) throw new IllegalArgumentException("El gasto no puede ser null");
		if(gastos.containsKey(gasto.getId())) {
			return false;
		}
		gastos.put(gasto.getId(), gasto);
		return true;
	}

	@Override
	public boolean modificarGasto(Gasto gasto) {
		if(gasto==null) throw new IllegalArgumentException("El gasto no puede ser null");
		if(!gastos.containsKey(gasto.getId())) {
			return false;
		}
		gastos.put(gasto.getId(), gasto);
		return true;
	}

	@Override
	public boolean borrarGasto(UUID id) {
		if(id==null) throw new IllegalArgumentException("El id no puede ser null");
		if(!gastos.containsKey(id)) {
			return false;
		}
		gastos.remove(id);
		return true;
	}

	@Override
	public List<Gasto> listarGastos() {
		return List.copyOf(gastos.values());
	}

	@Override
	public Gasto buscarGasto(UUID id) {
		if(id==null) throw new IllegalArgumentException("El id no puede ser null");
		return gastos.get(id);
	}

}
