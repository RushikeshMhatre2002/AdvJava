package com.sunbeam.pojos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data // @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String passwd;
	private Date birth;
	private boolean status;
	private String role;
	// constructor
	// getters/setters
	// param constructor
	// toString()
}