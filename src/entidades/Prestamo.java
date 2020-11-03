package entidades;

import java.util.Date;

public class Prestamo {
	private int idEjemplar;
	private String titulo;
	private long idSocio;
	private String nombreSocio;
	private Date fechaPrestamo;
	private Date fechaLimiteDevolucion;
	private int diasDemora;
	
	public Prestamo() {
		
	}
	
	public int getIdEjemplar() {
		return idEjemplar;
	}
	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	public long getIdSocio() {
		return idSocio;
	}
	public void setIdSocio(long idSocio) {
		this.idSocio = idSocio;
	}
	
	
	public String getNombreSocio() {
		return nombreSocio;
	}
	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}
	
	
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	
	
	public Date getFechaLimiteDevolucion() {
		return fechaLimiteDevolucion;
	}
	
	
	public void setFechaLimiteDevolucion(Date fechaLimiteDevolucion) {
		this.fechaLimiteDevolucion = fechaLimiteDevolucion;
	}
	
	
	public int getDiasDemora() {
		return diasDemora;
	}
	public void setDiasDemora(int diasDemora) {
		this.diasDemora = diasDemora;
	}
	
	
	@Override
	public String toString() {
		return "Prestamo [idEjemplar=" + idEjemplar + ", titulo=" + titulo + ", idSocio=" + idSocio + ", nombreSocio="
				+ nombreSocio + ", fechaPrestamo=" + fechaPrestamo + ", fechaLimiteDevolucion=" + fechaLimiteDevolucion
				+ ", diasDemora=" + diasDemora + "]";
	}
	
	
}
