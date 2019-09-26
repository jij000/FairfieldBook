package edu.mum.cs.controller;

import edu.mum.cs.dto.UserDto;
import edu.mum.cs.model.Post;
import edu.mum.cs.dto.PostDto;
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
@WebServlet("/Admin/AdminUsers")
public class AdminUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUsersController() {
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
		TypedQuery<UserDto> query = em.createQuery("select new edu.mum.cs.dto.UserDto(u.id, u.name, u.profilePhotoUrl, cast(u.isActive as string)) from User u order by u.id desc ", UserDto.class);
		List<UserDto> userList = query.getResultList();
		// Close the EntityManager
		em.close();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
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
		TypedQuery<User> query = em.createQuery("from User where id = ?1 ", User.class);
		query.setParameter(1, Integer.parseInt(request.getParameter("id")));
		User user = query.getSingleResult();
		user.setActive(Boolean.valueOf(request.getParameter("isDisable")));
		em.merge(user);
		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PrintWriter out = response.getWriter();
		response.setContentType("application/text");
		response.setCharacterEncoding("UTF-8");
		out.write("success");
	}

}
