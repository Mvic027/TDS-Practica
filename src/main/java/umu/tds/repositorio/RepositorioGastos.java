package umu.tds.repositorio;

import java.util.List;
import java.util.UUID;

import umu.tds.dominio.Gasto;

public interface RepositorioGastos {
	boolean anadirGasto(Gasto gasto);

	boolean modificarGasto(Gasto gasto);

	boolean borrarGasto(UUID id);

	List<Gasto> listarGastos();

	//devuelve null si no existe
	Gasto buscarGasto(UUID id);
}
