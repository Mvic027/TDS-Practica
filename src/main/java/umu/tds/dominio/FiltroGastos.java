package umu.tds.dominio;

import java.time.LocalDate;
import java.time.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public final class FiltroGastos {

	private FiltroGastos() {}
	
	public static List<Gasto> filtrar (List<Gasto> gastos, List<Month> meses, LocalDate fechaInicio, LocalDate fechaFin, List<Categoria> categorias){
		Objects.requireNonNull(gastos,"La lista de gastos no puede estar vacía");
		if(fechaInicio == null && fechaFin == null && fechaInicio.isAfter(fechaFin)) {
			throw new IllegalArgumentException("No puede haber una fecha null ni fechaInicio > fechaFin");
		}
		//Formamos la condicion del stream, que empieza aceptando todo
		Predicate<Gasto> condicion = g -> true;
		
		//Añadimos las condiciones de meses, fecha inicio...
		if( meses != null && !meses.isEmpty()) {
			condicion = condicion.and(g -> meses.contains(g.getFecha().getMonth()));
		}
		
		if(fechaInicio != null) {
			condicion = condicion.and(g -> !g.getFecha().isBefore(fechaInicio));
		}
		
		if(fechaFin != null) {
			condicion = condicion.and(g -> !g.getFecha().isAfter(fechaFin));
		}
		
		if(categorias != null && !categorias.isEmpty()) {
			condicion = condicion.and(g -> categorias.contains(g.getCategoria()));
		}
		
		return gastos.stream()
				.filter(condicion)
				.toList();
	}
		
}
