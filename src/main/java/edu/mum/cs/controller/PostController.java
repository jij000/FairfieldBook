package edu.mum.cs.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mum.cs.model.Advertisement;
import edu.mum.cs.model.Post;
import edu.mum.cs.model.User;
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
@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostController() {
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
		User user = (User)request.getSession().getAttribute("user");
		TypedQuery<Post> query = em.createQuery(" from Post p where p.author.id = ?1", Post.class);
		query.setParameter(1, 2);
//		query.setParameter(1, user.getId());
		List<Post> postList = query.getResultList();
		// Close the EntityManager
		em.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		final Gson gson = builder.create();

		String jsonStr  = gson.toJson(postList);
		out.write(jsonStr);
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
		if ("add".equals(request.getParameter("mode"))) {
			Advertisement ad = new Advertisement();
			ad.setName(request.getParameter("name"));
			ad.setContent(request.getParameter("content"));
			ad.setDisable(Boolean.parseBoolean(request.getParameter("isDisable")));
			em.persist(ad);
		} else {
			TypedQuery<Advertisement> query = em.createQuery("from Advertisement where id = ?1 ", Advertisement.class);
			query.setParameter(1, Integer.parseInt(request.getParameter("id")));
			Advertisement ad = query.getSingleResult();
			ad.setDisable(Boolean.parseBoolean(request.getParameter("isDisable")));
			em.merge(ad);
		}
		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PrintWriter out = response.getWriter();
		response.setContentType("application/text");
		response.setCharacterEncoding("UTF-8");
		out.write("success");
	}

}
