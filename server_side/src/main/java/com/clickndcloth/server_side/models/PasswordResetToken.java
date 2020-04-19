package com.clickndcloth.server_side.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Password_reset")
public class PasswordResetToken implements Serializable {

	private static final long serialVersionUID = 2129061884317452155L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String token;
	
	private int user_id;
	

	
	
//	@OneToOne
//	@JoinTable(name = "User",
//    joinColumns = @JoinColumn(name = "fk_Password_reset_User", referencedColumnName ="id"),
//    		inverseJoinColumns = @JoinColumn(name = "user_id" , referencedColumnName="id"))
//	private User user;
	
//	@OneToOne
//	joinColumns = @JoinColumn(name="id",  referencedColumnName ="id"),
//	inverseJoinColumns = @JoinColumn(name = "User_id" , referencedColumnName="id"))
//	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}
