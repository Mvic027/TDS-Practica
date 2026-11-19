package umu.tds.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import umu.tds.dominio.Gasto;

public class RepositorioGastosMemoria implements RepositorioGastos {
	//Solo se crea una instancia de este repositorio para que no se duplique y asegurar que se guarda todo en la misma
	private static RepositorioGastosMemoria unicaInstancia = null;
	
	private final Map<UUID, Gasto> gastos = new HashMap<>();
	
	//Constructor privado para que nadie pueda hacer un 'new' desde fuera
	private RepositorioGastosMemoria() {
	    // Vacío
	}
	
	//Metodo para obtener un repositorio de gastos
	public static RepositorioGastosMemoria getInstancia() {
	    if (unicaInstancia == null) {
	        unicaInstancia = new RepositorioGastosMemoria();
	    }
	    return unicaInstancia;
	}
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
	public Optional<Gasto> buscarGasto(UUID id) {
		if(id==null) throw new IllegalArgumentException("El id no puede ser null");
		return Optional.ofNullable(gastos.get(id));
	}

}