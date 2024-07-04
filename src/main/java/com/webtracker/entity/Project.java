package com.webtracker.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;

	    @OneToMany (mappedBy = "project",cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private Set<Timesheet> timesheets;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Timesheet> getTimesheets() {
			return timesheets;
		}

		public void setTimesheets(Set<Timesheet> timesheets) {
			this.timesheets = timesheets;
		}

	    

}
