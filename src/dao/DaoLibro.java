package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Libro;

public class DaoLibro {
	
	//Metodo para listar pintar el listado de libros
	public ArrayList<Libro> listadoLibros(String criterioDeBusqueda, String valorDeBusqueda)throws SQLException, Exception{
		ArrayList<Libro> listaLibros;
		listaLibros = new ArrayList<Libro>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		String autor = null;
		String titulo = null;
		String isbn = null;
		
		if(criterioDeBusqueda.equals("autor")) {
			autor = "%" + valorDeBusqueda + "%"; 
			titulo = "%";
			isbn = "%";
		}else if(criterioDeBusqueda.equals("titulo")) {
			autor = "%";
			titulo = "%" + valorDeBusqueda + "%";
			isbn = "%";
		}else {
			autor = "%";
			titulo = "%";
			isbn = valorDeBusqueda;
		}
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			String ordenSQL ="SELECT L.ISBN,L.TITULO,A.NOMBRE AUTOR,EJEMPLARESTOTALES,EJEMPLARESENPRESTAMO, " + 
					"      (EJEMPLARESTOTALES-EJEMPLARESENPRESTAMO)EJEMPLARESDISPONIBLES " + 
					"FROM LIBRO L,AUTOR A, " + 
					"(" + 
					"SELECT A.ISBN,EJEMPLARESTOTALES,NVL(EJEMPLARESENPRESTAMO,0)EJEMPLARESENPRESTAMO " + 
					"FROM " + 
					"  ( " + 
					"    SELECT L.ISBN,COUNT(*)EJEMPLARESTOTALES " + 
					"    FROM LIBRO L,EJEMPLAR E " + 
					"    WHERE L.ISBN=E.ISBN " + 
					"    AND E.BAJA='N' " + 
					"    GROUP BY L.ISBN " + 
					"  )A LEFT JOIN " + 
					"    ( SELECT ISBN,COUNT(*) EJEMPLARESENPRESTAMO " + 
					"      FROM PRESTAMO P,EJEMPLAR E " + 
					"      WHERE P.IDEJEMPLAR=E.IDEJEMPLAR "  + 
					"      GROUP BY ISBN)B " + 
					"ON A.ISBN=B.ISBN)B " + 
					"WHERE L.ISBN=B.ISBN " + 
					"AND L.IDAUTOR=A.IDAUTOR " + 
					"AND TRANSLATE(UPPER(A.NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U') " + 
					"AND TRANSLATE(UPPER(L.TITULO),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE  TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U') " + 
					"AND L.ISBN LIKE ? " + 
					"ORDER BY AUTOR,TITULO "; 
			st = con.prepareStatement(ordenSQL);
			st.setString(1, autor);
			st.setString(2, titulo);
			st.setString(3, isbn);
			rs = st.executeQuery();
			while(rs.next()) {
				Libro libro = new Libro();
				libro.setIsbn(rs.getString("ISBN"));
				libro.setTitulo(rs.getString("TITULO"));
				libro.setNombreAutor(rs.getString("AUTOR"));
				libro.setEjemplaresTotales(rs.getInt("EJEMPLARESTOTALES"));
				libro.setEjemplaresEnPrestamo(rs.getInt("EJEMPLARESENPRESTAMO"));
				libro.setEjemplaresDisponibles(rs.getInt("EJEMPLARESDISPONIBLES"));
				listaLibros.add(libro);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
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
