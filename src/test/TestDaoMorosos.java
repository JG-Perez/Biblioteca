package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoSocio;
import entidades.Socio;

public class TestDaoMorosos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoSocio moroso = new DaoSocio();
		try {
			ArrayList<Socio>listadoMorosos = moroso.listadoSociosMorosos();
			for(Socio s: listadoMorosos) {
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
