package edu.mum.cs.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mum.cs.dto.PostAndFollowDto;
import edu.mum.cs.model.Advertisement;
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
import java.util.ArrayList;
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

		String sql = "SELECT p.id, u.id authorId, u.name authorName, u.profilePhotoUrl, p.content, p.picUrl, p.isFromTwitter, p.isDisable, " +
				"case when p.author_id = " + String.valueOf(user.getId()) + " then " + String.valueOf(user.getId()) + " else f.followingUserList_id end canFollow\n" +
				"\tFROM Post p left join User u on u.id = p.author_id \n" +
				"\tleft join following f on f.followingUserList_id = p.author_id and f.User_id = ?1\n" +
				"\twhere (p.author_id = ?2 or p.author_id in (select followingUserList_id from following where User_id = ?2))";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, user.getId());
		if ("".equals(request.getParameter("id"))) {
			query.setParameter(2, user.getId());
		} else {
			query.setParameter(2, request.getParameter("id"));
		}

//		query.setParameter(1, user.getId());
		List<PostAndFollowDto> postList = query.getResultList();
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
		User user = (User)request.getSession().getAttribute("user");
		// Open a EntityManager
		EntityManager em = FBUtility.getEntityManager(request.getServletContext());
		em.getTransaction().begin();

		Post post = new Post();
		post.setAuthor(user);
		post.setContent(request.getParameter("content"));
		post.setPicUrl(request.getParameter("picUrl"));
		post.setFromTwitter(false);
		post.setDisable(false);
		em.persist(post);

		// Close the EntityManager
		em.getTransaction().commit();
		em.close();

		PostAndFollowDto pDto = new PostAndFollowDto();
		pDto.setId(post.getId());
		pDto.setAuthorId(post.getAuthor().getId());
		pDto.setAuthorName(post.getAuthor().getName());
		pDto.setProfilePhotoUrl(post.getAuthor().getProfilePhotoUrl());
		pDto.setContent(post.getContent());
		pDto.setPicUrl(post.getPicUrl());
		pDto.setFromTwitter(post.isFromTwitter());
		pDto.setDisable(post.isDisable());
		pDto.setCanFollow("1");

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();

		final Gson gson = builder.create();

		String jsonStr  = gson.toJson(pDto);
		out.write(jsonStr);
	}

}
