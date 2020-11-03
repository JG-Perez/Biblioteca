package test;

import java.sql.SQLException;

import dao.DaoAutor;
import entidades.Autor;

public class TestFindAutorById {

	public static void main(String[] args) {
		DaoAutor dao = new DaoAutor();
		try {
			Autor a = dao.findAutorById(1);
			if(a != null) {
				System.out.println(a.toString());
			}else {
				System.out.println("Autor inexistente");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
