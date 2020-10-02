package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoAutor;
import entidades.Autor;

public class TestDaoAutor {

	public static void main(String[] args) {
		DaoAutor dao = new DaoAutor();
		try {
			ArrayList<Autor>listadoAutores = dao.listadoAutores();
			for(Autor a: listadoAutores) {
				System.out.println(a.toString());
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
