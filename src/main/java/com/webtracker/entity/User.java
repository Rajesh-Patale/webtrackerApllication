package com.webtracker.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String username;
	@NotNull
	private String password;
	
	@OneToMany (mappedBy = "user",cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Timesheet> timesheet;
	
	@OneToMany (mappedBy = "user" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<ScreenActivity> screenactivity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Timesheet> getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Set<Timesheet> timesheet) {
		this.timesheet = timesheet;
	}

	public Set<ScreenActivity> getScreenactivity() {
		return screenactivity;
	}

	public void setScreenactivity(Set<ScreenActivity> screenactivity) {
		this.screenactivity = screenactivity;
	}
	
	

}
