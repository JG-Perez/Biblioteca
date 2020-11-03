package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Autor;
import entidades.Socio;

public class DaoSocio {
	
	public DaoSocio() {
		
	}
	
	public ArrayList<Socio> listadoSocios() throws SQLException, Exception {
		ArrayList<Socio> listasocios;
		listasocios = new ArrayList<Socio>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM SOCIO ORDER BY NOMBRE";
			rs = st.executeQuery(ordenSQL);
			while(rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdSocio(rs.getInt("IDSOCIO"));
				miSocio.setEmail(rs.getString("EMAIL"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listasocios.add(miSocio);
			}
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
		}
		return listasocios;
	}
	//Metodo para buscar socios morosos
	public ArrayList<Socio> listadoSociosMorosos() throws SQLException, Exception {
		ArrayList<Socio> listaSocios;
		listaSocios = new ArrayList<Socio>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT IDSOCIO, NOMBRE " +
								"FROM SOCIO S " + 
								"WHERE IDSOCIO IN(SELECT IDSOCIO " +
													"FROM PRESTAMO " +
													"WHERE FECHALIMITEDEVOLUCION<SYSDATE) " +
								"ORDER BY S.NOMBRE";
			rs = st.executeQuery(ordenSQL);
			while(rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdSocio(rs.getLong("IDSOCIO"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				listaSocios.add(miSocio);
			}
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return listaSocios;
	}
	
	//Metodo para modificar socio
	public void modificarSocio(Socio s) throws SQLException, Exception{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null; //sentencia
		PreparedStatement sl = null; // orden select
		int socioActualizado = 0; // Para obtener el total de registros actualizados
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String selectSQL = "SELECT 'X' FROM SOCIO WHERE IDSOCIO = ? FOR UPDATE WAIT 3";
			sl = con.prepareStatement(selectSQL);
			sl.setLong(1, s.getIdSocio());
			rs = sl.executeQuery();
			String ordenSQL = "UPDATE SOCIO SET NOMBRE = ?, DIRECCION = ? WHERE IDSOCIO = ?"; 
			st = con.prepareStatement(ordenSQL);
			st.setString(1,s.getNombre());
			st.setString(2,s.getDireccion());
			st.setLong(3, s.getIdSocio());
			socioActualizado = st.executeUpdate(); //Usamos executeUpdate para actualizar
			con.commit();
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
		}
	}
	
	//Metodod para buscar socio por subcadena
	
	public ArrayList<Socio>listadoSociosPorNombre(String subcadena)throws SQLException, Exception{
		
		ArrayList<Socio> listaSocios;
		listaSocios = new ArrayList<Socio>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT IDSOCIO, NOMBRE, DIRECCION FROM SOCIO " + 
								"WHERE UPPER(NOMBRE) LIKE UPPER(?)";
			st = con.prepareStatement(ordenSQL);
			st.setString(1,"%" + subcadena.toUpperCase() + "%"); // utilizo % para lo que pueda ir antes o detras de mi subcadena
			rs = st.executeQuery();
			while(rs.next()) {
				Socio miSocio = new Socio();
				miSocio.setIdSocio(rs.getLong("IDSOCIO"));
				miSocio.setNombre(rs.getString("NOMBRE"));
				miSocio.setDireccion(rs.getString("DIRECCION"));
				listaSocios.add(miSocio);
			}
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
		}
		return listaSocios;
	}
	
	//Metodo para buscar socios por id
	public Socio buscarSocioPorId(long idSocio)throws SQLException, Exception {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		Socio socio = new Socio();
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL = "SELECT IDSOCIO, NOMBRE, DIRECCION FROM SOCIO WHERE IDSOCIO = ?";
			st = con.prepareStatement(ordenSQL);
			st.setLong(1, idSocio);
			rs = st.executeQuery(); // no escribimos la consulta aqui ya la tenemos en el prepareStatement
			while(rs.next()) {
				socio.setIdSocio(rs.getLong("IDSOCIO"));
				socio.setNombre(rs.getString("NOMBRE"));
				socio.setDireccion(rs.getString("DIRECCION"));
			}
			
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return socio;
		
	}
}
