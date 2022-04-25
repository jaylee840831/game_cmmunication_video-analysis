package com.example.hibernateTest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hibernateTest.StudentRequest;
import com.example.hibernateTest.config.ConfigProp;
import com.example.hibernateTest.dto.Laptop;
import com.example.hibernateTest.dto.Student;
import com.example.hibernateTest.repository.LaptopRepository;
import com.example.hibernateTest.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	LaptopRepository laptopRepository;

	public Student setStudent(StudentRequest studentRequest) {
		
		Student student=new Student(studentRequest.rollno,studentRequest.name,studentRequest.marks);
		Set<Laptop>laptops=new HashSet<Laptop>();
		
		for(Laptop laptop:studentRequest.laptops) {
			Laptop L = laptop;
			if(laptop.getLid()!=0) {
				L=laptopRepository.findByLid(laptop.getLid());
			}
			laptop.getStudent().add(student);
			laptops.add(laptop);
		}
		
		student.setLaptop(laptops);
		
		ConfigProp configProp=new ConfigProp();
		
		return (Student) configProp.saveData(student);
	}
	
	public Set<Student> setRandomStudents() {
		
		ConfigProp configProp=new ConfigProp();
		
		Set<Laptop>student_laptops=new HashSet<Laptop>();
		Set<Laptop>laptops=new HashSet<Laptop>();
		
		Set<Student>students=new HashSet<Student>();
		Random random = new Random();
		
		student_laptops.add(new Laptop(101,"asus"));
		student_laptops.add(new Laptop(102,"dell"));
		student_laptops.add(new Laptop(103,"微星"));
		
		for(int i=1;i<=10;i++) {
			Student student=new Student(i,"name"+i,random.nextInt(50));
			
			for(Laptop laptop:student_laptops) {
				Laptop L = laptop;
//				if(laptop.getLid()!=0) {
//					L=laptopRepository.findByLid(laptop.getLid());
//				}
				laptop.getStudent().add(student);
				laptops.add(laptop);
			}
			
			student.setLaptop(laptops);
			
			students.add(student);
		}
		
		List<Student> al = new ArrayList<>();
		for(Student s:students) {
			al.add(s);
		}
		
		Collections.sort(al, new Comparator<Student>(){

			  public int compare(Student o1, Student o2)
			  {
			     return Integer.compare(o1.getRollno(), o2.getRollno()) ;
			  }
			});
		
		configProp.saveDatas(al);
		
		return students;
	}
	
	public List<Student> nameGetStudent(String name1,String name2) {
		ConfigProp configProp=new ConfigProp();
		return configProp.getData(name1,name2);
	}
	
	public List<Student> rollnoGetStudent(String rollno1,int rollno2) {
		ConfigProp configProp=new ConfigProp();
		return configProp.getData(rollno1,rollno2);
	}
}
