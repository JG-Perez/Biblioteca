package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Prestamo;

public class DaoPrestamo {
	
	public DaoPrestamo() {
		//libros sin debolver talba prestamo
	}
	public ArrayList<Prestamo>listadoLibrosFueraPlazo(int socio)throws SQLException, Exception{
		ArrayList<Prestamo> listaLibros;
		listaLibros = new ArrayList<Prestamo>();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		
		try {
			Conexion miConex = new Conexion();
			con = miConex.getConexion();
			String ordenSQL="SELECT IDEJEMPLAR, IDSOCIO, NOMBRE, TITULO, DIAS_DEMORA " +
					"FROM(SELECT P.IDEJEMPLAR, P.IDSOCIO, S.NOMBRE, L.TITULO,(TRUNC(SYSDATE)-TRUNC(FECHALIMITEDEVOLUCION))DIAS_DEMORA " +
					"FROM SOCIO S,PRESTAMO P, EJEMPLAR E, LIBRO L " +
					"WHERE S.IDSOCIO = P.IDSOCIO "+
					"AND P.IDEJEMPLAR = E.IDEJEMPLAR "+
					"AND E.ISBN = L.ISBN " +
					"AND TRUNC(FECHALIMITEDEVOLUCION)<TRUNC(SYSDATE)) "+
					"WHERE IDSOCIO = ?";
			PreparedStatement  sentencia = con.prepareStatement(ordenSQL);
			sentencia.setInt(1, socio);
			rs = sentencia.executeQuery();
			while(rs.next()) {
				Prestamo miPrestamo = new Prestamo();
				miPrestamo.setIdEjemplar(rs.getInt("IDEJEMPLAR"));
				miPrestamo.setIdSocio(rs.getLong("IDSOCIO"));
				miPrestamo.setNombreSocio(rs.getString("NOMBRE"));
				miPrestamo.setTitulo(rs.getString("TITULO"));
				miPrestamo.setDiasDemora(rs.getInt("DIAS_DEMORA"));
				listaLibros.add(miPrestamo);
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
		
	 return listaLibros;
	}

}
