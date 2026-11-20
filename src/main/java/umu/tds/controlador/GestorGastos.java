package umu.tds.controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import umu.tds.dominio.Categoria;
import umu.tds.dominio.Gasto;
import umu.tds.repositorio.RepositorioGastos;
import umu.tds.repositorio.RepositorioGastosMemoria;


//Clase Singleton, es decir que solamente va a haber una instancia a la vez
public class GestorGastos {
	private static GestorGastos unicaInstancia;
	private final RepositorioGastos repositorioGastos;
	
	private GestorGastos() {
		repositorioGastos = RepositorioGastosMemoria.getInstancia();
	}
	
	public static GestorGastos getUnicaInstancia() {
		if(unicaInstancia==null) {
			unicaInstancia = new GestorGastos();
		}
		return unicaInstancia;
	}
	
	public boolean anadirGasto(String concepto, double cuantia, LocalDate fecha, Categoria categoria) {
		Gasto gasto = new Gasto(concepto, cuantia, fecha, categoria);
		return repositorioGastos.anadirGasto(gasto);
	}
	
	public boolean modificarGasto(UUID id, String concepto, double cuantia, LocalDate fecha, Categoria categoria) {
	    return repositorioGastos.buscarGasto(id)
	            .map(g -> {
	                g.actualizar(concepto, cuantia, fecha, categoria);
	                return repositorioGastos.modificarGasto(g);
	            })
	            .orElse(false);
	}
	
	public boolean borrarGasto(UUID id) {
		return repositorioGastos.borrarGasto(id);
	}
	
	public List<Gasto> obtenerGastos(){
		return repositorioGastos.listarGastos();
	}
	
	
}
