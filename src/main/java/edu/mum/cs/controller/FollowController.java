package edu.mum.cs.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mum.cs.dto.PostAndFollowDto;
import edu.mum.cs.model.Post;
import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
@WebServlet("/FollowController")
public class FollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FollowController() {
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
			throws IOException {
		// Open a EntityManager
		EntityManager em = FBUtility.getEntityManager(request.getServletContext());
		User user = (User)request.getSession().getAttribute("user");

		List<User> userList;
		if ("following".equals(request.getParameter("mode"))) {
			TypedQuery<User> tq = em.createQuery("select u.followingUserList from User u where id=?1 ", User.class);
			tq.setParameter(1, request.getParameter("id"));
			userList = tq.getResultList();
		} else {
			TypedQuery<User> tq = em.createQuery("select u.followerUserList from User u where id=?1 ", User.class);
			tq.setParameter(1, request.getParameter("id"));
			userList = tq.getResultList();
		}
		// Close the EntityManager
		em.close();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		final Gson gson = builder.create();

		String jsonStr  = gson.toJson(userList);
		out.write(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User)request.getSession().getAttribute("user");
		// Open a EntityManager
		EntityManager em = FBUtility.getEntityManager(request.getServletContext());
		em.getTransaction().begin();

		TypedQuery<User> tq = em.createQuery("from User where id=?1 ",User.class);
		tq.setParameter(1,request.getParameter("id"));

		User targetUser = tq.getSingleResult();
		if (targetUser != null) {
			user.getFollowingUserList().add(targetUser);
			targetUser.getFollowerUserList().add(user);
		}
		em.merge(user);
		em.merge(targetUser);

		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		final Gson gson = builder.create();

		String jsonStr  = gson.toJson(targetUser);
		out.write(jsonStr);
	}

}
