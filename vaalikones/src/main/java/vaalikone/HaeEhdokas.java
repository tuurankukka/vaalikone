package vaalikone;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;

/**
 * Servlet implementation class HaeEhdokas
 */
public class HaeEhdokas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HaeEhdokas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {

			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();

			String id = request.getParameter("id");
			Integer ehdokas_id = Integer.parseInt(id);

			System.out.println("Haettava ehdokas=" + id);
			System.out.println("Haettava ehdokas_id=" + ehdokas_id);

			Query q = em.createQuery("SELECT e FROM Ehdokkaat e WHERE e.ehdokasId=?1");
			q.setParameter(1, ehdokas_id);
			List<Ehdokkaat> kaikkiEhdokkaat = (List<Ehdokkaat>) (q.getResultList());

			if (kaikkiEhdokkaat == null) {
				System.out.println("HaeEhdokas Ei ehdokkaita");
			} else {
				System.out.println("HaeEhdokas ON ehdokkaita " + kaikkiEhdokkaat.size());
			}

			RequestDispatcher rd = request.getRequestDispatcher("EhdokkaanMuokkaus.jsp");
			request.setAttribute("ehdokasLista", kaikkiEhdokkaat);
			rd.forward(request, response);

		}

		catch (Exception e) {
			System.out.println("Ongelmia: " + e.getMessage());
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
// select * from ehdokkaat where sukunimi like merkkijono ("aa%")
