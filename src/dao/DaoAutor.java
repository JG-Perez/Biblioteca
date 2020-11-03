package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	/***************************************************************/
	//Metodo para insertar autor recibe como parametro un objeto Autor completo
	public void insertaAutor(Autor a) throws SQLException, Exception {
		Connection con=null;
		PreparedStatement st=null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String ordenSQL = "INSERT INTO AUTOR VALUES(S_AUTOR.NEXTVAL,?,?)";
					st = con.prepareStatement(ordenSQL);
			st.setString(1, a.getNombre()); //Para hacer la insercion necesitamos un objeto Autor
			st.setDate(2,a.getFechaNacimiento());
			st.executeUpdate(); // Para ver los registros afectados podemos guardar esto en una variable
			con.commit();
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		}
		finally{
			if(st!=null)
				st.close();
			if(con!=null)
				con.close();
		}
	}
	/*************************************************************/
	//Metodo para añadir Autor con PLSQL
	public void addAutorPLSQL(Autor a) throws SQLException, Exception {
		Connection con = null;
		CallableStatement cs = null;

		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String ordenSQL = "{call ADDAUTOR(?,?)}";
			cs = con.prepareCall(ordenSQL);
			cs.setString(1, a.getNombre());
			cs.setDate(2, a.getFechaNacimiento());
			cs.executeUpdate();
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}finally{
			if(cs!=null)
				cs.close();
			if(con!=null)
				con.close();
		}
		
	}
	/************************************************************/
	//Metodo para buscar autor por id
	public Autor findAutorById(int idautor)throws SQLException, Exception {
		Autor a = null;
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Conexion miconex = new Conexion();
			con = miconex.getConexion();
			con.setAutoCommit(false);
			String ordenSQL = "SELECT IDAUTO,NOMBRE,FECHANACIMIENTO FROM AUTOR WHERE IDAUTOR=?";
			st = con.prepareStatement(ordenSQL);
			st.setInt(1, idautor);
			rs = st.executeQuery(); //No pasamos la orden como parametro ya que la hemos pasado aqui con.prepareStatement(ordenSQL);
			if(rs.next()) {
				a = new Autor();
				a.setIdAutor(rs.getInt("IDAUTOR"));
				a.setNombre(rs.getString("NOMBRE"));
				a.setFechaNacimiento(rs.getDate("FECHANACIMIENTO"));
			}
		}catch(SQLException se) {
			throw se;
		}catch(Exception e) {
			throw e;
		}
		finally {
			if(st!=null) {
				st.close();
			}
			if(con!=null) {
				con.close();
			}
		}
		return a;
	}
	
}
