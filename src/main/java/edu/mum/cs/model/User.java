package edu.mum.cs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private SystemRole role;

	private String profilePhotoUrl;
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
}
