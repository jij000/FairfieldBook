package edu.mum.cs.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	@Expose
	private String name;
	@Expose
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	@Expose
	private SystemRole role;

	@Expose
	private String profilePhotoUrl;
	@Type(type="yes_no")
	@Column(nullable = false)
	@Expose
	private boolean isActive;
	@OneToMany
	@JoinTable(name = "following")
	private List<User> followingUserList;
	@OneToMany
	@JoinTable(name = "follower")
	private List<User> followerUserList;
	@OneToMany(mappedBy = "author")
	private List<Post> posts;

	private String token;
	private String tokenSecret;

	@Expose
	private int followerNum;
	@Expose
	private int followingNum;
}
