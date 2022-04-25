package com.example.hibernateTest.dto;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="student")
public class Student {
 @Id
 private int rollno;
 
 @Column(name = "name")
 private String name;
 
 @Column(name = "marks")
 private int marks;
 
@ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
@JoinTable(name = "student_laptop",
		 joinColumns= {@JoinColumn(name="student_rollno")}
 , inverseJoinColumns= {@JoinColumn(name="laptop_lid")})
//@JsonIgnore
 private Set<Laptop> laptop= new HashSet<>();

public Student() {
	
}

public Student(int rollno, String name, int mark) {
	super();
	this.rollno = rollno;
	this.name = name;
	this.marks = mark;
}

 
public int getRollno() {
	return rollno;
}
public void setRollno(int rollno) {
	this.rollno = rollno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}
public Set<Laptop> getLaptop() {
	return laptop;
}
public void setLaptop(Set<Laptop> laptops) {
	this.laptop = laptops;
}

@Override
public String toString() {
	return "Student [rollno=" + rollno + ", name=" + name + ", marks=" + marks + "]";
}

 
}
