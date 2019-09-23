package edu.mum.cs.controller;

import com.google.gson.Gson;
import edu.mum.cs.model.Advertisement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/Advertisement")
public class AdvertisementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("fairfieldBook");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdvertisementController() {
		super();
		// TODO Auto-generated constructor stub
//		// Open a EntityManager
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//
//		// Close the EntityManager
//		em.getTransaction().commit();
//		em.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Open a EntityManager
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Advertisement> query = em.createQuery("from Advertisement", Advertisement.class);
		List<Advertisement> adsList = query.getResultList();
		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(new Gson().toJson(adsList));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Open a EntityManager
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Advertisement ad = new Advertisement();
		ad.setContent(request.getParameter("content"));
		ad.setName(request.getParameter("name"));
		ad.setDisabled(Boolean.valueOf(request.getParameter("isDisable")));
		em.persist(ad);

		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
