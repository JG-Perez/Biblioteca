package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoAutor;
import dao.DaoLibro;
import dao.DaoPrestamo;
import dao.DaoSocio;
import entidades.Autor;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("/controllerAdmin")
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		DaoSocio daoSocio = null;
		DaoAutor daoAutor = null; //No se si esto va bien aqui o es mejor declarar e instanciar abajo en la operacion.
		DaoPrestamo daoPrestamo = null;
		int codigoSocio;
		switch(operacion) {
		case "listarSocios":
			daoSocio = new DaoSocio();
			try {
				ArrayList<Socio>listadoSocios = daoSocio.listadoSocios();
				request.setAttribute("listadoSocios", listadoSocios);
				request.getRequestDispatcher("admin/listadoSocios.jsp").forward(request, response);
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "altaAutor":
			request.getRequestDispatcher("admin/altaAutor.jsp").forward(request, response); //Paso por el controlador
			break;
		case "insertaAutor":
			Autor autor = new Autor();
			Date fechaNacimiento = new Date();
			String fechaFormulario = request.getParameter("fechaNacimiento");
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd-MM-yyyy"); //aqui damos formato a la fecha
			formatoDeFecha.setLenient(false);
			System.out.println(request.getParameter("nombre"));
			System.out.println(request.getParameter(fechaFormulario));
			try {
				autor.setNombre(request.getParameter("nombre"));
				fechaNacimiento = formatoDeFecha.parse(fechaFormulario);
				LocalDate localDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				autor.setFechaNacimiento(java.sql.Date.valueOf(localDate));
				daoAutor = new DaoAutor();
				daoAutor.insertaAutor(autor);
				request.setAttribute("confirmarOperacion", "Autor creado correctamente");
				request.getRequestDispatcher("admin/altaAutor.jsp").forward(request, response);
			}catch(ParseException pe ) {
				request.setAttribute("nuevoAutor", autor);
				request.setAttribute("fechaErronea", fechaFormulario);
				procesarError(request, response, pe, "admin/altaAutor.jsp"); //Envio la request,la response el error y la ruta del jsp al metodo
			}catch(SQLException se ) {
				procesarErrorSQL(request, response, se, "admin/altaAutor.jsp");
			}catch(Exception e) {
				procesarError(request, response, e, null);
			}
			
			break;
		case "sociosLibrosFueraPlazo":
			daoSocio = new DaoSocio();
			try {
				ArrayList<Socio>listaSociosMorosos = daoSocio.listadoSociosMorosos();
				request.setAttribute("listaSociosMorosos", listaSociosMorosos);
				request.getRequestDispatcher("admin/listadoSociosMorosos.jsp").forward(request, response);
			}catch(SQLException e){
				procesarError(request, response, e, null);
			}catch(Exception e) {
				procesarError(request, response, e, null);
			}
			break;
			
		case "mostrarLibrosFueraPlazo":
			daoSocio = new DaoSocio();
			try {
				ArrayList<Socio>listaSociosMorosos = daoSocio.listadoSociosMorosos();
				request.setAttribute("listaSociosMorosos", listaSociosMorosos);
			}catch(SQLException e){
				procesarError(request, response, e, null);
			}catch(Exception e) {
				procesarError(request, response, e, null);
			}
			
			codigoSocio = Integer.parseInt(request.getParameter("socio"));
			daoPrestamo = new DaoPrestamo();
			try {
				ArrayList<Prestamo>listadoPrestamos=new ArrayList<>();
				listadoPrestamos = daoPrestamo.listadoLibrosFueraPlazo(codigoSocio);
				request.setAttribute("librosFueraDePlazo", listadoPrestamos);
				request.getRequestDispatcher("admin/listadoSociosMorosos.jsp").forward(request, response);
			}catch(SQLException se) {
				procesarErrorSQL(request, response, se, null);
			}catch(Exception e) {
				procesarError(request, response, e, null);
			}
			break;
			
		case "verSocioAModificar":
			daoSocio = new DaoSocio();
			try {
				String subcadena = request.getParameter("buscaSubcadena");
				ArrayList<Socio>socioAModificar = daoSocio.listadoSociosPorNombre(subcadena);
				request.setAttribute("socioAModificar", socioAModificar);
				request.setAttribute("subcadena", subcadena);
				request.getRequestDispatcher("admin/getSocio.jsp").forward(request, response);
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "editarCamposSocio":
			daoSocio = new DaoSocio();
			long idSocio = Long.parseLong(request.getParameter("socio"));
			try {
				Socio socio = daoSocio.buscarSocioPorId(idSocio);
				request.setAttribute("socioAModificar", socio);
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
				
			}catch(SQLException e){
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "actualizarSocio":
			daoSocio = new DaoSocio();
			Socio socio = new Socio();
			long codigoDeSocio = Long.parseLong(request.getParameter("idSocio"));
			socio.setIdSocio(codigoDeSocio);
			socio.setNombre(request.getParameter("nombre"));
			socio.setDireccion(request.getParameter("direccion"));
			try {
				daoSocio.modificarSocio(socio);
				request.setAttribute("socioAModificar", socio);
				request.setAttribute("confrimarOperacion", "Socio modificado");
				request.getRequestDispatcher("admin/modificarSocio.jsp").forward(request, response);
			}catch(SQLException se) {
				
			}catch(Exception e) {
				
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
				request.getRequestDispatcher("admin/borrarEjemplar.jsp").forward(request, response);
			}catch(SQLException se) {
				procesarErrorSQL(request, response, se, null);
			}catch(Exception e) {
				procesarError(request, response, e, null);
			}
			break;
		}
		
	}
	/************************************************************************************************************/
	//Metodos para procesar error al insertar la fecha en formato invalido en el proceso de insertar autor
	protected void procesarError(HttpServletRequest request, HttpServletResponse response, Exception e, String url)throws ServletException, IOException {
		String mensajeError = e.getMessage();
		if (e instanceof ParseException)
			mensajeError = "Formato de fecha no válido";
		request.setAttribute("error", mensajeError);
		request.getRequestDispatcher(url).forward(request, response);
	}

	//Metodo para procesar errores de tipo SQL
	protected void procesarErrorSQL(HttpServletRequest request, HttpServletResponse response, SQLException e, String url) throws ServletException, IOException {

		int codigoError = e.getErrorCode();
		String mensajeErrorUsuario;
		switch (codigoError) {
		case 30006:
			mensajeErrorUsuario = "Registro en proceso de modificacion. Intentelo mas tarde";
			
			request.setAttribute("error", mensajeErrorUsuario);
			request.getRequestDispatcher(url).forward(request, response);
			break;
		case 1:
		case 2291:
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
