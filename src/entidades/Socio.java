package entidades;

public class Socio {
	private long idSocio;
	private String email;
	private String nombre;
	private String direccion;
	
	
	public Socio() {
		
	}


	public long getIdSocio() {
		return idSocio;
	}
	public void setIdSocio(long idSocio) {
		this.idSocio = idSocio;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return "Socio [idSocio=" + idSocio + ", email=" + email + ", nombre=" + nombre + ", direccion=" + direccion
				+ "]";
	}

}
