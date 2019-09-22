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
	private SystemRole role;
	private String profilePhotoUrl;
	private boolean isActive;
	@OneToMany
	private List<User> following;
}
