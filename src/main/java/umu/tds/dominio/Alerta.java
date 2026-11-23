package umu.tds.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Alerta {
	
	private final UUID id;
	private final double limite;
	private final Categoria categoria;
	private final EstrategiaAlerta estrategia;
	private LocalDate ultimaFechaNotificada;
	
	public Alerta(UUID id, double limite, Categoria categoria, EstrategiaAlerta estrategia) {
		Objects.requireNonNull(id,"El id no puede ser null");
		Objects.requireNonNull(estrategia, "La estrategia no puede ser vacía");
		if(limite<= 0)
			throw new IllegalArgumentException("El limite tiene que ser mayor que 0");
		
		this.id = id;
		this.limite = limite;
		this.categoria = categoria;
		this.estrategia = estrategia;
	}

	public Alerta(double limite, EstrategiaAlerta estrategia) {
		this(UUID.randomUUID(),limite,null,estrategia);
	}

	public Alerta(double limite, Categoria categoria, EstrategiaAlerta estrategia) {
		this(UUID.randomUUID(),limite,Objects.requireNonNull(categoria, "La categoria no puede ser nula"),estrategia);
	}

	public UUID getId() {
		return id;
	}

	public double getLimite() {
		return limite;
	}

	public Optional<Categoria> getCategoria() {
		return Optional.ofNullable(categoria);
	}

	public EstrategiaAlerta getEstrategia() {
		return estrategia;
	}

	public Optional<LocalDate> getUltimaFechaNotificada() {
		return Optional.ofNullable(ultimaFechaNotificada);
	}

	public Optional<Notificacion> evaluar(List<Gasto> gastos, LocalDate fechaReferencia){
		//Comprobamos que los parametros no sean null
		Objects.requireNonNull(gastos, "Debe haber al menos un gasto");
		Objects.requireNonNull(fechaReferencia,"Debe haber una fecha para poder evaluar");
		
		//Ahora calculamos total gastado
		double totalGastado = estrategia.calcularGastoEnPeriodo(gastos, getCategoria(), fechaReferencia);
		
		//Vemos si nos hemos pasado del limite y si estamos en la fecha
		if(totalGastado > limite && (ultimaFechaNotificada == null || !estrategia.esMismoPeriodo(fechaReferencia, ultimaFechaNotificada))) {
			this.ultimaFechaNotificada = fechaReferencia;
			String detallesCat = getCategoria()
					.map(c -> "en " + c.getNombre())
					.orElse("");
			String mensaje = "Se ha superado el limite " + estrategia.getNombrePeriodo() + ". El límite era de " + this.limite + " y se han gastado " + totalGastado + "€ " + detallesCat.trim();
			return Optional.of(new Notificacion(mensaje, id));
		}
		
		return Optional.empty();
		
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alerta other = (Alerta) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
