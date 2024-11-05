package com.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
class Employee
{
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String name;
 private int age;
 
 @OneToMany(mappedBy = "emp")
 private List<Project> pro;

public Employee() {
	super();
	// TODO Auto-generated constructor stub
}

public Employee(int id, String name, int age, List<Project> pro) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.pro = pro;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public List<Project> getpro() {
	return pro;
}

public void setEmp(List<Project> pro) {
	this.pro= pro;
}
 
}
@Entity
class Project 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
	private int pid;
	private String pname;
	
	@ManyToOne
	@JoinColumn(name="emp_id")
	private Employee emp;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(int pid, String pname, Employee emp) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.emp = emp;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}


 class EmpDemo
 {

	public static void main(String[] args)
	{
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
		
		Transaction t = s.beginTransaction();
		
		/*
		User user = new User();
	    user.setUsername("Pratik");
	    
	    Task task1 = new Task();
	    task1.setTitle("Development");
	    task1.setUser(user);
	    
	    Task task2 = new Task();
	    task2.setTitle("Testing");
	    task2.setUser(user);
	    
        List<Task> tasks = new ArrayList<Task>();
	    tasks.add(task1); 
	    tasks.add(task2);
	    
	    user.setTasks(tasks);
        */
		Employee e1 = new Employee();
		e1.setId(1);
		e1.setName("Karuna Mishra");
		e1.setAge(22);
		
		
		Project p1 = new Project();
		p1.setPid(11); p1.setPname("Karuna");  p1.setEmp(e1);
		
		Project p2 = new Project();
		p2.setPid(12); p2.setPname("Priya");  p2.setEmp(e1);
		
		List<Project> pro = new ArrayList<Project>();
		pro.add(p1);
		pro.add(p2);
		
		e1.setEmp(pro);
		
		s.save(e1); s.save(p1); s.save(p2);
		
		t.commit();
		s.close();
		
		
		
		
		
	}

}
