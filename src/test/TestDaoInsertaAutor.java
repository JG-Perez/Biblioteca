package test;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.DaoAutor;
import entidades.Autor;

public class TestDaoInsertaAutor {

	public static void main(String[] args) {
		DaoAutor dao = new DaoAutor();
		Autor a = new Autor();
		a.setNombre("Arturo Pérez Reverte");
		LocalDate fecha_nacimiento = LocalDate.of(1951, 11, 25);
		java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fecha_nacimiento);
		a.setFechaNacimiento(fechaNacimiento);
		try {
			dao.addAutorPLSQL(a); //PLSQL
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
