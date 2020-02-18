package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persist.Ehdokkaat;

/**
 * Servlet implementation class MuokkaaEhdokas
 */

public class HaeEhdokasLista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HaeEhdokasLista() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {

			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();

//			emf = Persistence.createEntityManagerFactory("vaalikones");
//			em = emf.createEntityManager();

			Query q = em.createQuery("SELECT e FROM Ehdokkaat e");

			List<Ehdokkaat> kaikkiEhdokkaat = (List<Ehdokkaat>) (q.getResultList());

			RequestDispatcher rd = request.getRequestDispatcher("EhdokasLista.jsp");
			request.setAttribute("ehdokasLista", kaikkiEhdokkaat);
			rd.forward(request, response);
		}

		catch (Exception e) {

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
