package vaalikone;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;

/**
 * Servlet implementation class EhdokkaanLisaaminen
 */
public class TallennaEhdokas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TallennaEhdokas() {
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
//		Integer id = request.getParameter("id");
		String sukunimi = request.getParameter("sukunimi");
		String etunimi = request.getParameter("etunimi");
		String puolue = request.getParameter("puolue");
		String kotipaikkakunta = request.getParameter("kotipaikkakunta");
		Integer ika = Integer.parseInt(request.getParameter("ika"));
		String miksi = request.getParameter("miksieduskuntaan");
		String mita = request.getParameter("mitahaluat");
		String ammatti = request.getParameter("ammatti");

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("vaalikones");
			em = emf.createEntityManager();
			em.getTransaction().begin();

			Ehdokkaat eh = new Ehdokkaat();
			eh.setSukunimi(sukunimi);
			eh.setEtunimi(etunimi);
			eh.setPuolue(puolue);
			eh.setKotipaikkakunta(kotipaikkakunta);
			eh.setIka(ika);
			eh.setMiksiEduskuntaan(miksi);
			eh.setMitaAsioitaHaluatEdistaa(mita);
			eh.setAmmatti(ammatti);
			em.persist(eh);
			em.getTransaction().commit();
			em.close();

		} catch (Exception e) {
			response.getWriter().println("EMF+EM EI Onnistu");
		}

		response.sendRedirect("/EhdokkaanLisays.jsp");
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
