package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexiones.Conexion;
import entidades.Autor;

public class DaoAutor {
	public DaoAutor() {
	}
	public ArrayList<Autor> listadoAutores() throws SQLException, Exception {
		ArrayList<Autor> listaautores;
		listaautores=new ArrayList<Autor>();
		Connection con=null;
		ResultSet rs=null;
		Statement st=null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			st = con.createStatement();
			String ordenSQL = "SELECT * FROM AUTOR ORDER BY NOMBRE";
			rs = st.executeQuery(ordenSQL);
			while (rs.next()) {
				Autor miAutor = new Autor();
				miAutor.setIdAutor(rs.getInt("IDAUTOR"));
				miAutor.setNombre(rs.getString("NOMBRE"));
				miAutor.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
				listaautores.add(miAutor);
			}
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		}
		finally{
			if(rs!=null)
				rs.close();
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		}
		return listaautores;
	}
}
