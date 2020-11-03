package test;

import java.sql.SQLException;

import dao.DaoSocio;
import entidades.Socio;

public class TestModificaSocio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socio socio = new Socio();
		socio.setNombre("SOCIO21");
		socio.setDireccion("C/ SOCIO21 SN");
		socio.setIdSocio(19);
		DaoSocio daoSocio = new DaoSocio();
		try {
			daoSocio.modificarSocio(socio);;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
