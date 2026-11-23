package umu.tds.dominio;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Notificacion {
	private final UUID id;
	private final String mensaje;
	private final LocalDateTime fechaGeneracion;
	private boolean leida;
	private final UUID idAlerta;
	
	public Notificacion(UUID id, String mensaje, UUID idAlerta) {
		Objects.requireNonNull(id, "El ID de la notificación no puede ser nulo");
		Objects.requireNonNull(idAlerta, "El ID de la alerta no puede ser nulo");
		if (mensaje == null || mensaje.isBlank()) {
		    throw new IllegalArgumentException("El mensaje de la notificación no puede estar vacío");
		}
		this.id = id;
		this.mensaje = mensaje.trim();
		this.fechaGeneracion = LocalDateTime.now();
		this.leida = false ;
		this.idAlerta = idAlerta;
	}

	public Notificacion(String mensaje, UUID idAlerta) {
		this(UUID.randomUUID(),mensaje,idAlerta);
	}
	
	public UUID getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public LocalDateTime getFechaGeneracion() {
		return fechaGeneracion;
	}

	public boolean isLeida() {
		return leida;
	}

	public UUID getIdAlerta() {
		return idAlerta;
	}

	public void marcarComoLeida() {
		this.leida=true;
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
		Notificacion other = (Notificacion) obj;
		return Objects.equals(id, other.id);
	}
	
	

	
}
