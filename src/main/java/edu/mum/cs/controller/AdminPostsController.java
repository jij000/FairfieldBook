package edu.mum.cs.controller;

import edu.mum.cs.model.Post;
import edu.mum.cs.dto.PostDto;
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
@WebServlet("/Admin/AdminPosts")
public class AdminPostsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPostsController() {
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
		TypedQuery<PostDto> query = em.createQuery("select new edu.mum.cs.dto.PostDto(p.id, p.author.name, p.content, cast(p.isDisable as string)) from Post p order by p.id desc ", PostDto.class);
		List<PostDto> postList = query.getResultList();
		// Close the EntityManager
		em.close();
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("/admin/post.jsp").forward(request, response);
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
		TypedQuery<Post> query = em.createQuery("from Post where id = ?1 ", Post.class);
		query.setParameter(1, Integer.parseInt(request.getParameter("id")));
		Post post = query.getSingleResult();
		post.setDisable(Boolean.valueOf(request.getParameter("isDisable")));
		em.merge(post);
		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PrintWriter out = response.getWriter();
		response.setContentType("application/text");
		response.setCharacterEncoding("UTF-8");
		out.write("success");
	}

}
