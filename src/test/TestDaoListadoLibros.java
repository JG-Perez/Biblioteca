package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoLibro;
import entidades.Libro;

public class TestDaoListadoLibros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoLibro daoLibro = new DaoLibro();
		try {
			ArrayList<Libro>listadoLibros = daoLibro.listadoLibros("titulo", "la");
			for(Libro l : listadoLibros) {
				System.out.println(l.toString());
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
