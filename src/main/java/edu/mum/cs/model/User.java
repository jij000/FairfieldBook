package edu.mum.cs.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

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
	@Expose
	private boolean isActive;
	@OneToMany
	@JoinTable(name = "follow")
	private List<User> followingUserList;
	@OneToMany(mappedBy = "author")
	private List<Post> posts;

}
