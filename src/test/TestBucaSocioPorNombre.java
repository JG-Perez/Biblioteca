package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoSocio;
import entidades.Socio;

public class TestBucaSocioPorNombre {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Socio>listadoSocios = null;
		DaoSocio dao = new DaoSocio();
		try {
			listadoSocios = dao.listadoSociosPorNombre("socio");
			for(Socio s: listadoSocios) {
				System.out.println(s.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
