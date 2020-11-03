package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoSocio;
import entidades.Socio;

public class TestDaoSocio {

	public static void main(String[] args) {
		DaoSocio dao = new DaoSocio();
		try {
			ArrayList<Socio>listadoSocios = dao.listadoSocios();
			for(Socio s : listadoSocios) {
				System.out.println(s.toString());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
