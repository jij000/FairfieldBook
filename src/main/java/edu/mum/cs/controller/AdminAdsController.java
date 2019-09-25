package edu.mum.cs.controller;

import edu.mum.cs.dto.PostDto;
import edu.mum.cs.model.Advertisement;
import edu.mum.cs.model.Post;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
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
@WebServlet("/AdminAds")
public class AdminAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAdsController() {
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
		EntityManager em = FBUtility.getEntityManager(request.getServletContext());
		TypedQuery<Advertisement> query = em.createQuery(" from Advertisement p", Advertisement.class);
		List<Advertisement> adList = query.getResultList();
		// Close the EntityManager
		em.close();
		request.setAttribute("adList", adList);
		request.getRequestDispatcher("/admin/advertisement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Open a EntityManager
		EntityManager em = FBUtility.getEntityManager(request.getServletContext());
		em.getTransaction().begin();
		TypedQuery<Advertisement> query = em.createQuery("from Advertisement where id = ?1 ", Advertisement.class);
		query.setParameter(1, Integer.parseInt(request.getParameter("id")));
		Advertisement ad = query.getSingleResult();
		ad.setDisable(Boolean.valueOf(request.getParameter("isDisable")));
		em.merge(ad);
		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PrintWriter out = response.getWriter();
		response.setContentType("application/text");
		response.setCharacterEncoding("UTF-8");
		out.write("success");
	}

}
