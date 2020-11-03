package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAutor;
import dao.DaoLibro;
import entidades.Autor;
import entidades.Libro;



/**
 * Servlet implementation class ControllerSocio
 */
@WebServlet("/controllerSocio")
public class ControllerSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerSocio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		DaoAutor daoautor = new DaoAutor();
		switch(operacion) {
		case "listarAutores":
			try {
				ArrayList<Autor>listadoAutores = daoautor.listadoAutores();
				request.setAttribute("listadoAutores", listadoAutores);
				request.getRequestDispatcher("socios/listadoAutores.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "consultarLibro":
			String criterioDeBusqueda = request.getParameter("criterioDeBusqueda");
			String valorDeBusqueda = request.getParameter("valorDeBusqueda");
			request.setAttribute("criterioDeBusqueda", criterioDeBusqueda);
			request.setAttribute("valorDeBusqueda", valorDeBusqueda);
			DaoLibro daolibro = new DaoLibro();
			try {
				ArrayList<Libro>listadoLibros = daolibro.listadoLibros(criterioDeBusqueda, valorDeBusqueda);
				request.setAttribute("librosBuscados", listadoLibros);
				request.getRequestDispatcher("socios/getLibros.jsp").forward(request, response);
			}catch(SQLException se) {
				procesarErrorSQL(request, response, se, null);
			}catch(Exception e) {
				procesarError(request, response, e, null);
			}
			break;
		case "xxxx":
			
			
		}
	}
	
	//Metodos para procesar error al insertar la fecha en formato invalido en el proceso de insertar autor
		protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e, String url)throws ServletException, IOException {
			String mensajeError = e.getMessage();
			if (e instanceof ParseException)
				mensajeError = "Formato de fecha no válido";
			request.setAttribute("error", mensajeError);
			request.getRequestDispatcher(url).forward(request, response);
		}


		protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e, String url) throws ServletException, IOException {

			int codigoError = e.getErrorCode();
			String mensajeErrorUsuario;
			switch (codigoError) {
			case 30006:
				mensajeErrorUsuario = "Registro en proceso de modificacion. Intentelo mas tarde";
				
				request.setAttribute("error", mensajeErrorUsuario);
				request.getRequestDispatcher(url).forward(request, response);
				break;
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
