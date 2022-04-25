package com.example.hibernateTest.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="laptop")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Laptop {
	@Id
	private int lid;
	
	@Column(name = "lname")
	private String lname;
	
//	 @NotFound(action = NotFoundAction.IGNORE)
	 @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL,mappedBy = "laptop")
	 @JsonIgnore
	private Set<Student> student= new HashSet<>();
	 
	 public Laptop() {
			
	 }

	 public Laptop(int lid,String lname) {
	 	super();
	 	this.lid = lid;
	 	this.lname = lname;
	 }
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Set<Student> getStudent() {
		return student;
	}
	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Laptop [lid=" + lid + ", lname=" + lname +"]";
	}
}
