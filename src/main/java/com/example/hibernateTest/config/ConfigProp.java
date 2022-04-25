package com.example.hibernateTest.config;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.hibernateTest.dto.Laptop;
import com.example.hibernateTest.dto.Student;

public class ConfigProp {
	
	public Object saveData(Object o) {
		Session session=null;
		try {
			Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
		    ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory sf = config.buildSessionFactory(registry);
			
			session=sf.openSession();
			session.beginTransaction();
			session.merge(o);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return o;
	}
	
	public Object saveDatas(List<?> o) {
		Session session=null;
		try {
			Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
		    ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory sf = config.buildSessionFactory(registry);
			
			session=sf.openSession();
			session.beginTransaction();
			
			for(Object object:o) {
				session.saveOrUpdate(object);
			}
			session.getTransaction().commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		
		return o;
	}
	
	//get student
	@SuppressWarnings("unchecked")
	public List<Student> getData(String str,Object o) {
		
		List<Student>result=new ArrayList<>();
		
		if(str.equals("name")) {
			Session session=null;
			try {
				Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
			    ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				SessionFactory sf = config.buildSessionFactory(registry);
				
				session=sf.openSession();
		      	session.beginTransaction();
				
		      	Query<Student> q=session.createQuery("from Student");
		      	result=q.list();
		      	
				session.getTransaction().commit();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null) {
					session.close();
				}
			}
		}
		else if(str.equals("rollno")) {
			Session session=null;
			Student student=null;
			try {
				Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
			    ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				SessionFactory sf = config.buildSessionFactory(registry);
				
				session=sf.openSession();
		      	session.beginTransaction();
		      	
		      	Query<Student> q=session.createQuery("from Student");
		      	result=q.list();
		      	
				session.getTransaction().commit();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				if(session!=null) {
					session.close();
				}
			}
		}
		
		
		return result;
	}
	
	//get laptop
	public Laptop getData(int lid) {
		Session session=null;
		Laptop laptop=null;
		try {
			Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
		    ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory sf = config.buildSessionFactory(registry);
			
			session=sf.openSession();
	      	session.beginTransaction();
	      	laptop=session.get(Laptop.class, lid);
			session.getTransaction().commit();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return laptop;
	}
	
	//second cache get laptop
	@SuppressWarnings("unchecked")
	public Laptop getData() {
		Session session=null;
		Laptop laptop=null;
		try {
			
			Configuration config= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
		    ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			SessionFactory sf = config.buildSessionFactory(registry);
			
			System.out.println("hibernate 二級快取 use query  (HQL)");
			
			// first cache
			session=sf.openSession();
	      	session.beginTransaction();
	      	
	      	Query<Laptop> q1=session.createQuery("from Laptop l where l.lid= :lid");
	      	q1.setParameter("lid", 102);
	      	q1.setCacheable(true);
	      	laptop=q1.uniqueResult();
	      	
//	      	laptop=session.get(Laptop.class,101);
	      	
	      	System.out.println(laptop.toString());
	      	
			session.getTransaction().commit();
			session.close();
			
			///////////////////////////////////////////////////////////////////////////////////////
			
			//second cache
			session=sf.openSession();
	      	session.beginTransaction();
	      	
	      	Query<Laptop> q2=session.createQuery("from Laptop l where l.lid= :lid");
	      	q2.setParameter("lid", 102);
	      	q2.setCacheable(true);
	      	laptop=q2.uniqueResult();
	      	
//	      	laptop=session.get(Laptop.class,101);
	      	
	      	System.out.println(laptop.toString());
	      	
			session.getTransaction().commit();
			session.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return laptop;
	}
}
