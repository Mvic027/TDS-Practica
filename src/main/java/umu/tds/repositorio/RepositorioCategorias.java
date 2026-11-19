package umu.tds.repositorio;

import java.util.List;
import java.util.Optional;
import umu.tds.dominio.Categoria;

public interface RepositorioCategorias {

	boolean anadirCategoria(Categoria categoria);

	List<Categoria> listarCategorias();

	Optional<Categoria> buscarCategoria(String nombre);
	
}
