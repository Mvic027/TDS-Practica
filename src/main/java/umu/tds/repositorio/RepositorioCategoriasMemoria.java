package umu.tds.repositorio;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import umu.tds.dominio.Categoria;

public class RepositorioCategoriasMemoria implements RepositorioCategorias {

    // TODO 1: Atributo estático privado para la única instancia (Singleton)

	private static RepositorioCategoriasMemoria unicoRepositorio = null;
	
	
    private final Set<Categoria> categorias = new HashSet<>();

    // TODO 2: Constructor PRIVADO. 
    // Dentro de él, añade las categorías predefinidas: "Alimentación", "Transporte", "Entretenimiento"
    
    
    private RepositorioCategoriasMemoria() {
    	anadirCategoria(new Categoria("Alimentación"));
		anadirCategoria(new Categoria("Transporte"));
		anadirCategoria(new Categoria("Entretenimiento"));
    }
    
    // TODO 3: Método público estático getInstancia()
    public static RepositorioCategoriasMemoria getInstancia() {
    	if(unicoRepositorio == null) {
    		unicoRepositorio = new RepositorioCategoriasMemoria();
    	}
        return unicoRepositorio;
    }

   

    @Override
    public boolean anadirCategoria(Categoria categoria) {
        // TODO 4: Valida que no sea null (lanza IllegalArgumentException) y añádela al Set.
        // Pista: el método .add() de un Set ya devuelve true si se añadió o false si ya existía.
    	if(categoria == null) {
    		throw new IllegalArgumentException("La categoría no puede ser null");
    	}
    	
        return categorias.add(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return List.copyOf(categorias);
    }

    @Override
    public Optional<Categoria> buscarCategoria(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return Optional.empty();
        }
        // TODO 5: Usa un Stream sobre 'categorias' para filtrar la que coincida en nombre (equalsIgnoreCase)
        // y devuelve .findFirst()
        return categorias.stream()
        		.filter(c -> c.getNombre().equalsIgnoreCase(nombre.trim()))
        		.findFirst();
    }
}
