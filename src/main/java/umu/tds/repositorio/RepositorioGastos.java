package umu.tds.repositorio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import umu.tds.dominio.Gasto;

public interface RepositorioGastos {
	boolean anadirGasto(Gasto gasto);

	boolean modificarGasto(Gasto gasto);

	boolean borrarGasto(UUID id);

	List<Gasto> listarGastos();

	Optional<Gasto> buscarGasto(UUID id);
}

